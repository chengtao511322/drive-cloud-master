package com.drive.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.system.pojo.entity.UserEntity;
import com.drive.system.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户信息 Mapper 接口
 *
 * @author xiaoguo
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    UserVo getUserVoByUserName(String userName);

    List<UserVo> getUserList(Page page, @Param(Constants.WRAPPER) QueryWrapper<UserEntity> wrapper);
}

