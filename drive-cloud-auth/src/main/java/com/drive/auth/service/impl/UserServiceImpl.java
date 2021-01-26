package com.drive.auth.service.impl;


import com.drive.auth.mapper.UserMapper;
import com.drive.auth.pojo.entity.UserEntity;
import com.drive.auth.pojo.vo.UserVo;
import com.drive.auth.service.UserService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.security.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 用户信息 服务实现类
 *
 * @author DreamChan
 */
@Service
public class UserServiceImpl extends BaseService<UserMapper, UserEntity> implements UserService {

    @Override
    public UserVo getUserVoByUserName(String userName) {
        return this.baseMapper.getUserVoByUserName(userName);
    }

    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(userId)) {
            roles.add(Constants.DEFAULT_ADMIN_ROLE);
        } else {
            List<String> perms = this.baseMapper.getRolePermissionByUserId(userId);
            for (String perm : perms) {
                if (StringUtils.isNotNull(perm)) {
                    roles.addAll(Arrays.asList(perm.trim().split(",")));
                }
            }
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> menus = new HashSet<>();
        // 管理员拥有所有权限
        if (SecurityUtils.isAdmin(userId)) {
            menus.add("*:*:*");
        } else {
            List<String> perms = this.baseMapper.getMenuPermsByUserId(userId);
            for (String perm : perms) {
                if (StringUtils.isNotBlank(perm)) {
                    menus.addAll(Arrays.asList(perm.trim().split(",")));
                }
            }
        }
        return menus;
    }

}


