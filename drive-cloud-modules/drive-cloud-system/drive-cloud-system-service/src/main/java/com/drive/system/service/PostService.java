package com.drive.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.system.pojo.entity.PostEntity;

import java.util.List;

/**
 *
 * 岗位信息 服务类
 *
 * @author xiaoguo
 */
public interface PostService extends IService<PostEntity>{

    List<Integer> selectPostListByUserId(Long userId);

}

