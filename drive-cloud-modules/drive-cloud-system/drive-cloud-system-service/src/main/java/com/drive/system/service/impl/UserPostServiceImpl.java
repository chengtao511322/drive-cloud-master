package com.drive.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.system.mapper.UserPostMapper;
import com.drive.system.pojo.entity.UserPostEntity;
import com.drive.system.service.UserPostService;
import org.springframework.stereotype.Service;

/**
 * 用户与岗位关联 服务实现类
 *
 * @author xiaoguo
 * @since 2020-07-15
 */
@Service
public class UserPostServiceImpl extends ServiceImpl<UserPostMapper, UserPostEntity> implements UserPostService {

}

