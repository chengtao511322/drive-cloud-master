package com.drive.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.system.pojo.entity.PostEntity;

import java.util.List;

/**
 * 岗位信息 Mapper 接口
 *
 * @author xiaoguo
 */
public interface PostMapper extends BaseMapper<PostEntity> {

    List<PostEntity> selectPostsByUserName(String userName);

    List<Integer> selectPostListByUserId(Long userId);
}

