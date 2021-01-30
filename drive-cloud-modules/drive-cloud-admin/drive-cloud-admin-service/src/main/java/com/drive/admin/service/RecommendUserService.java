package com.drive.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.entity.RecommendUserEntity;

/**
 *
 * 推广人员信息表 服务类
 *
 * @author xiaoguo
 */
public interface RecommendUserService extends IService<RecommendUserEntity>{

    /**
     * 通过学员ID查询
     * @param studentId
     * @return
     */
    RecommendUserEntity getRecommendUserByStudentId(String studentId);
}

