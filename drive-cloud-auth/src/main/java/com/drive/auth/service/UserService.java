package com.drive.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.auth.pojo.entity.UserEntity;
import com.drive.auth.pojo.vo.UserVo;

import java.util.Set;

/**
 *
 * 用户信息 服务类
 *
 * @author DreamChan
 */
public interface UserService extends IService<UserEntity> {

    UserVo getUserVoByUserName(String userName);


    Set<String> getRolePermission(Long userId);


    Set<String> getMenuPermission(Long userId);

}

