package com.drive.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.exception.CustomException;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.system.mapper.RoleMapper;
import com.drive.system.pojo.dto.RoleEditParam;
import com.drive.system.pojo.entity.RoleDeptEntity;
import com.drive.system.pojo.entity.RoleEntity;
import com.drive.system.pojo.entity.RoleMenuEntity;
import com.drive.system.service.RoleDeptService;
import com.drive.system.service.RoleMenuService;
import com.drive.system.service.RoleService;
import com.drive.system.service.mapstruct.RoleMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 角色信息 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class RoleServiceImpl extends BaseService<RoleMapper, RoleEntity> implements RoleService {

    @Autowired
    private RoleMapStruct roleMapStruct;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleDeptService roleDeptService;

    /**
     * 获取角色集合
     *
     * @param
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRoleListByUserId(Long userId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(userId)) {
            roles.add(Constants.DEFAULT_ADMIN_ROLE);
        } else {
            List<RoleEntity> perms = this.baseMapper.selectRolePermissionByUserId(userId);
            for (RoleEntity perm : perms) {
                if (StringUtils.isNotNull(perm)) {
                    roles.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
                }
            }
        }
        return roles;
    }

    @Override
    public Set<String> getRoleListAll() {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限

        List<RoleEntity> perms = this.baseMapper.selectRolePermissionAll();
        for (RoleEntity perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                roles.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }

        return roles;
    }

    @Override
    public List<Integer> selectRoleListByUserId(Long userId) {
        return this.baseMapper.selectRoleListByUserId(userId);
    }

    @Override
    @Transactional
    public Integer insertRole(RoleEditParam roleEditParam) {

        RoleEntity roleEntity = roleMapStruct.toEntity(roleEditParam);
        int rows = this.baseMapper.insert(roleEntity);
        Long roleId = roleEntity.getRoleId();

        insertRoleMenu(roleId, roleEditParam.getMenuIds());

        return rows;
    }

    @Override
    @Transactional
    public Integer updateRole(RoleEditParam roleEditParam) {
        Long roleId = roleEditParam.getRoleId();

        // 修改角色信息
        int rows = this.baseMapper.updateById(roleMapStruct.toEntity(roleEditParam));

        // 删除角色与菜单关联
        LambdaQueryWrapper<RoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenuEntity::getRoleId, roleId);
        roleMenuService.remove(queryWrapper);

        insertRoleMenu(roleId, roleEditParam.getMenuIds());
        return rows;
    }

    @Override
    @Transactional
    public void authDataScope(RoleEditParam roleEditParam) {
        Long roleId = roleEditParam.getRoleId();

        // 删除角色与部门关联
        LambdaQueryWrapper<RoleDeptEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleDeptEntity::getRoleId, roleId);
        roleDeptService.remove(queryWrapper);
        // 修改信息
        this.getBaseMapper().updateById(BeanConvertUtils.copy(roleEditParam,RoleEntity.class));
        // 新增角色和部门信息（数据权限）
        insertRoleDept(roleId, roleEditParam.getDeptIds());
    }


    /**
     * 新增角色菜单信息
     */
    public Boolean insertRoleMenu(Long roleId, Long[] menuIds) {
        // 新增用户与角色管理
        List<RoleMenuEntity> list = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuEntity rm = new RoleMenuEntity();
            rm.setRoleId(roleId);
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            return roleMenuService.saveBatch(list);
        }
        return false;
    }

    /**
     * 新增角色部门信息(数据权限)
     */
    public Boolean insertRoleDept(Long roleId, Long[] deptIds) {
        // 新增角色与部门（数据权限）管理
        List<RoleDeptEntity> list = new ArrayList<>();
        for (Long deptId : deptIds) {
            RoleDeptEntity rd = new RoleDeptEntity();
            rd.setRoleId(roleId);
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0) {
            return roleDeptService.saveBatch(list);
        }
        return false;
    }

    /**
     * 校验角色是否允许操作
     */
    @Override
    public void checkRoleAllowed(RoleEditParam roleEditParam) {
        if (StringUtils.isNotNull(roleEditParam.getRoleId()) && roleEditParam.getRoleId() == Constants.ADMIN_ROLE_ID) {
            throw new CustomException("不允许操作超级管理员角色");
        }
    }
}

