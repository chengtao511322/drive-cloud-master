package com.drive.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.system.mapper.RoleMenuMapper;
import com.drive.system.pojo.entity.RoleMenuEntity;
import com.drive.system.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联 服务实现类
 *
 * @author DreamChan
 * @since 2020-07-15
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {

    @Override
    public boolean checkMenuExistRole(Long menuId) {
        int count = this.lambdaQuery().eq(RoleMenuEntity::getMenuId, menuId).count();
        return count > 0 ? true : false;
    }
}

