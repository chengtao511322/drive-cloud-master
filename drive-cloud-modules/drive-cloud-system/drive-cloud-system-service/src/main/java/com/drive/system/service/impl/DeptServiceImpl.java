package com.drive.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.enums.StatusEnum;
import com.drive.common.core.exception.CustomException;
import com.drive.common.core.tree.TreeSelect;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.core.utils.TreeUtil;
import com.drive.common.redis.service.RedisService;
import com.drive.system.mapper.DeptMapper;
import com.drive.system.pojo.entity.DeptEntity;
import com.drive.system.pojo.vo.DeptVo;
import com.drive.system.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门 服务实现类
 *
 * @author DreamChan
 */
@Service
public class DeptServiceImpl extends BaseService<DeptMapper, DeptEntity> implements DeptService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 根据角色查询所有的部门
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> selectDeptListByRoleId(Long roleId) {
        return this.baseMapper.selectDeptListByRoleId(roleId);
    }


    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<DeptVo> depts) {
        List<DeptVo> deptTrees = TreeUtil.build(depts, Constants.DEPT_ROOT_ID);
        return TreeUtil.buildTreeSelect(deptTrees);
    }

    @PostConstruct
    public void init(){
        List<DeptEntity> deptList = super.list();
        List<DeptVo> deptTrees =new ArrayList<>();
                deptList.stream().forEach((item) ->{
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("parent_id",item.getParentId());
            queryWrapper.groupBy("tenant_id");
            List<DeptEntity> deptParentList =super.list(queryWrapper);
            List<DeptVo> deptVoList = BeanConvertUtils.copyList(deptParentList,DeptVo.class);
           /* DeptVo deptVo = new DeptVo();
            deptVo.setChildren(deptVoList);
            deptVo.setDeptId(item.getDeptId());
            deptVo.setDeptName(item.getDeptName());
            deptTrees.add(deptVo);*/
            if (deptVoList.size() > 0){
                String result =  Optional.ofNullable(deptVoList)
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .filter(Objects::nonNull) //filtering null in Strings
                        .filter(name -> name.getTenantId() != null)
                        .map(n -> String.valueOf(n.getTenantId()))

                        .collect(Collectors.joining(","));
                stringRedisTemplate.opsForValue().set(CacheConstants.REDIS_DEPT_KEY + item.getDeptId(), result);
            }
        });
    }
    /**
     * 新增保存部门信息
     *
     * @param deptEntity 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(DeptEntity deptEntity) {
        DeptEntity parentDeptEntity = this.baseMapper.selectById(deptEntity.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (parentDeptEntity.getStatus() == StatusEnum.DISABLE.getCode()) {
            throw new CustomException("部门停用，不允许新增");
        }
        deptEntity.setParentIds(parentDeptEntity.getParentIds() + "," + deptEntity.getParentId());
        return this.baseMapper.insert(deptEntity);
    }
}

