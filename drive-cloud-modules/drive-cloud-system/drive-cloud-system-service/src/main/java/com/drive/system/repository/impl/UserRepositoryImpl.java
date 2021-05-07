package com.drive.system.repository.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.common.core.base.BaseController;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.redis.service.RedisService;
import com.drive.system.pojo.UserInfo;
import com.drive.system.pojo.dto.UserEditParam;
import com.drive.system.pojo.dto.UserPageQueryParam;
import com.drive.system.pojo.entity.UserEntity;
import com.drive.system.pojo.vo.UserVo;
import com.drive.system.repository.UserRepository;
import com.drive.system.service.MenuService;
import com.drive.system.service.RoleService;
import com.drive.system.service.UserService;
import com.drive.system.service.mapstruct.UserMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserRepositoryImpl extends BaseController implements UserRepository {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @Autowired
    private UserMapStruct userMapStruct;

    @Autowired
    private RedisService redisService;



    @Override
    public ResObject pageList(UserPageQueryParam param) {
        log.info(this.getClass() + "pageList-方法请求参数{}",param);
        Page<UserEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        IPage<UserEntity> pageList = userService.getUserList(page, this.getQueryWrapper(userMapStruct, param));
        //Page<UserVo> userVoPage = userMapStruct.toVoList(pageList);
       // log.info(this.getClass() + "pageList-方法请求结果{}",userVoPage);
        return R.success(pageList);
    }

    @PostConstruct
    public ResObject init(){
        QueryWrapper queryWrapper = new QueryWrapper();
        List<UserEntity> userList = userService.list(queryWrapper);
        userList.stream().forEach((item) ->{
            UserVo userVo = userService.getUserVoByUserName(item.getUserName());
            // 角色集合
            Set<String> roles = roleService.getRoleListByUserId(userVo.getUserId());
            // 权限集合
            Set<String> permissions = menuService.getMenuListByUserId(userVo.getUserId());

            UserInfo sysUserVo = new UserInfo();
            sysUserVo.setUserVo(userVo);
            sysUserVo.setRoles(roles);
            sysUserVo.setPermissions(permissions);
            redisService.set(CacheConstants.REDIS_USER_KEY +item.getUserName(), JSONObject.toJSON(sysUserVo));
        });
        return R.success();
    }

    @Override
    public ResObject findList(UserPageQueryParam param) {
        return null;
    }

    @Override
    public ResObject getById(String id) {
        return null;
    }

    @Override
    public ResObject getInfo(UserPageQueryParam param) {
        return null;
    }


    @Override
    public ResObject save(UserEditParam installParam) {
        return null;
    }

    @Override
    public ResObject update(UserEditParam updateParam) {
        return null;
    }

    @Override
    public ResObject deleteByIds(String[] ids) {
        return null;
    }

    @Override
    public ResObject deleteById(String id) {
        return null;
    }

    @Override
    public ResObject exportXls(UserPageQueryParam param, HttpServletResponse response) throws IOException {
        return null;
    }

    @Override
    public ResObject changeStatus(UserEditParam param) {
        return null;
    }
}
