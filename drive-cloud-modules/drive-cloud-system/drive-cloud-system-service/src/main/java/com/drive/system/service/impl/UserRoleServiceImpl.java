package com.drive.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.system.mapper.UserRoleMapper;
import com.drive.system.pojo.entity.UserRoleEntity;
import com.drive.system.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联 服务实现类
 *
 * @author xiaoguo
 * @since 2020-07-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

}

