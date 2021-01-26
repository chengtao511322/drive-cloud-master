package com.drive.system.service.impl;

import com.drive.common.core.base.BaseService;
import com.drive.system.mapper.PostMapper;
import com.drive.system.pojo.entity.PostEntity;
import com.drive.system.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 岗位信息 服务实现类
 *
 * @author DreamChan
 */
@Service
public class PostServiceImpl extends BaseService<PostMapper, PostEntity> implements PostService {

    @Override
    public List<Integer> selectPostListByUserId(Long userId) {
        return this.baseMapper.selectPostListByUserId(userId);
    }
}

