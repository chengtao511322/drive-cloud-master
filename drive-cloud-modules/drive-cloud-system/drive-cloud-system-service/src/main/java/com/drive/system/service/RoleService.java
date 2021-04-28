package com.drive.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.system.pojo.dto.RoleEditParam;
import com.drive.system.pojo.entity.RoleEntity;

import java.util.List;
import java.util.Set;

/**
 *
 * 角色信息 服务类
 *
 * @author xiaoguo
 */
public interface RoleService extends IService<RoleEntity>{

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> getRoleListByUserId(Long userId);
    Set<String> getRoleListAll();


    List<Integer> selectRoleListByUserId(Long userId);

    Integer insertRole(RoleEditParam roleEditParam);

    Integer updateRole(RoleEditParam roleEditParam);

    void authDataScope(RoleEditParam roleEditParam);

    void checkRoleAllowed(RoleEditParam roleEditParam);
}

