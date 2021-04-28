package com.drive.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.system.pojo.entity.RoleEntity;

import java.util.List;


/**
 * 角色信息 Mapper 接口
 *
 * @author xiaoguo
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<RoleEntity> selectRolePermissionByUserId(Long userId);
    List<RoleEntity> selectRolePermissionAll();

    List<RoleEntity> selectRolesByUserName(String userName);

    List<Integer> selectRoleListByUserId(Long userId);
}

