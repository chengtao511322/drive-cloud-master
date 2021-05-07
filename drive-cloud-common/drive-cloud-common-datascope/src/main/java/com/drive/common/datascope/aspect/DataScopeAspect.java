package com.drive.common.datascope.aspect;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.enums.DataScopeEnum;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.datascope.annotation.DataScope;
import com.drive.common.redis.service.RedisService;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.system.pojo.UserInfo;
import com.drive.system.pojo.entity.RoleEntity;
import com.drive.system.pojo.vo.UserVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据过滤处理
 *
 * @author DreamChan
 */
@Component
@Order(6)
@Aspect
public class DataScopeAspect {


    @Autowired
    private RedisService redisService;


    @Autowired
    private StringRedisTemplate redisTemplate;




    @Before("@annotation(dataScope)")
    public void doBefore(JoinPoint point, DataScope dataScope) {
        handleDataScope(point);
    }

    protected void handleDataScope(final JoinPoint joinPoint) {
        // 获得注解
        DataScope controllerDataScope = getAnnotationLog(joinPoint);
        if (controllerDataScope == null) {
            return;
        }
        // RemoteUserService remoteUserService = SpringContextUtil.getBean(RemoteUserService.class);
        // 获取当前的用户
        String username = SecurityUtils.getUsername();
        UserInfo userInfo = getUserInfo(username);
        UserVo currentUser = userInfo.getUserVo();
        String tenantId = SecurityUtils.getLoginUser().getTenantId();
        String operationId = SecurityUtils.getLoginUser().getOperationId();
        if (StrUtil.isNotEmpty(operationId)){
            currentUser.setOperationId(operationId);
        }
      /*  if (StrUtil.isNotEmpty(tenantId)){
            currentUser.setTenantId(tenantId);
        }*/

        if (currentUser != null) {
            // 如果是超级管理员，则不过滤数据
            if (currentUser.getUserId() != Constants.ADMIN_USER_ID) {
                dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(),
                        controllerDataScope.userAlias(),controllerDataScope.module());
            }
        }
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user      用户
     */
    public void dataScopeFilter(JoinPoint joinPoint, UserVo user, String deptAlias, String userAlias, String module) {
        StringBuilder sqlString = new StringBuilder();
        List<String> isArr = new ArrayList<>();
        String dataScopePermission = null;
        for (RoleEntity role : user.getRoles()) {
            String dataScope = role.getDataScope();
            // 判断
            if (DataScopeEnum.ADMIN_MODULE.getCode().equals(module)){
                if (DataScopeEnum.ALL.getCode().equals(dataScope)) {
                    sqlString = new StringBuilder();
                    break;
                } else if (DataScopeEnum.CUSTOM.getCode().equals(dataScope)) {
          /*          sqlString.append(StringUtils.format(
                            " OR %s.operator_id IN ( '%s' ) ", deptAlias,
                            getRoleDeptInfo(role.getRoleId())));*/
                    isArr = Arrays.asList(getRoleDeptInfo(role.getRoleId()) .split(",")).stream().map(s -> (s.trim())).collect(Collectors.toList());;
                } else if (DataScopeEnum.DEPT.getCode().equals(dataScope)) {
                    sqlString.append(StringUtils.format(" OR %s = '%s' ", deptAlias, this.getDeptInfo(user.getDeptId())));
                } else if (DataScopeEnum.DEPT_AND_CHILD.getCode().equals(dataScope)) {
                    /*sqlString.append(StringUtils.format(
                            " OR %s.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = %s or find_in_set( %s , parent_ids ) )",
                            deptAlias, user.getDeptId(), user.getDeptId()));*/
                    sqlString.append(StringUtils.format(
                            " OR %s IN ('%s')",
                            deptAlias, this.getDeptInfo(user.getDeptId())));
                } else if (DataScopeEnum.SELF.getCode().equals(dataScope)) {
                    if (StringUtils.isNotBlank(userAlias)) {
                        sqlString.append(StringUtils.format(" OR %s = '%s' ", userAlias, user.getOperationId()));

                    }else {
                        // 数据权限为仅本人且没有userAlias别名不查询任何数据
                        sqlString.append(" OR 1 = 0 ");
                    }
                }else if (DataScopeEnum.CUSTOM_SELF.getCode().equals(dataScope)) {
          /*          sqlString.append(StringUtils.format(
                            " OR %s.operator_id IN ( '%s' ) ", deptAlias,
                            getRoleDeptInfo(role.getRoleId())));*/
                    isArr = Arrays.asList(getRoleDeptInfo(role.getRoleId()) .split(",")).stream().map(s -> (s.trim())).collect(Collectors.toList());;
                    dataScopePermission =dataScope;
                }

            } else {
                if (DataScopeEnum.ALL.getCode().equals(dataScope)) {
                    sqlString = new StringBuilder();
                    break;
                } else if (DataScopeEnum.CUSTOM.getCode().equals(dataScope)) {
                    sqlString.append(StringUtils.format(
                            " OR %s.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = %s ) ", deptAlias,
                            role.getRoleId()));
                } else if (DataScopeEnum.DEPT.getCode().equals(dataScope)) {
                    sqlString.append(StringUtils.format(" OR %s.dept_id = %s ", deptAlias, user.getDeptId()));
                } else if (DataScopeEnum.DEPT_AND_CHILD.getCode().equals(dataScope)) {
                    sqlString.append(StringUtils.format(
                            " OR %s.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = %s or find_in_set( %s , parent_ids ) )",
                            deptAlias, user.getDeptId(), user.getDeptId()));
                } else if (DataScopeEnum.SELF.getCode().equals(dataScope)) {
                    if (StringUtils.isNotBlank(userAlias)) {
                        sqlString.append(StringUtils.format(" OR %s = %s ", userAlias, user.getUserId()));
                    } else {
                        // 数据权限为仅本人且没有userAlias别名不查询任何数据
                        sqlString.append(" OR 1 = 0 ");
                    }
                }
            }



        }

        if (StringUtils.isNotBlank(sqlString.toString())) {
            QueryWrapper queryWrapper = (QueryWrapper) joinPoint.getArgs()[1];
            //QueryWrapper queryWrapper = (QueryWrapper) joinPoint.getArgs()[1];
            queryWrapper.apply(sqlString.substring(4));
        }
        if (isArr.size() > 0){
            QueryWrapper queryWrapper = (QueryWrapper) joinPoint.getArgs()[1];

            queryWrapper.in( StringUtils.format("%s", deptAlias),isArr);
            if (DataScopeEnum.CUSTOM_SELF.getCode().equals(dataScopePermission)){
                queryWrapper.eq(StringUtils.format("%s", userAlias),user.getOperationId());
            }
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataScope getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(DataScope.class);
        }
        return null;
    }

    public UserInfo getUserInfo(String userName){
        if (StrUtil.isEmpty(userName)){
            return null;
        }

        UserInfo userInfo = JSONObject.parseObject(redisService.getStr(CacheConstants.REDIS_USER_KEY  + userName),UserInfo.class);
        if(StringUtils.isNull(userInfo)){
            return null;
        }
        return userInfo;
    }
    public String getDeptInfo(Long deptId){
        if (deptId == null){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String redisRes = redisTemplate.opsForValue().get(CacheConstants.REDIS_DEPT_KEY  + deptId);
        stringBuilder.append(deptId+",");
        stringBuilder.append(redisRes);
        return redisRes;
    }
    public String getRoleDeptInfo(Long roleId){
        if (roleId == null){
            return null;
        }
        String redisRes = redisTemplate.opsForValue().get(CacheConstants.REDIS_ROLE_DEPT_KEY  + roleId);
        return redisRes;
    }
}
