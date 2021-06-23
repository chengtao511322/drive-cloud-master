package com.drive.admin.repository;

import com.drive.admin.pojo.dto.RecommendUserEditParam;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 推广人员信息表 服务类
 *
 * @author xiaoguo
 */
public interface RecommendUserRepository extends BaseRepository<RecommendUserPageQueryParam, RecommendUserEditParam>{

    /**
     * 通过手机号查询推广商信息
     * @param phone
     * @return
     */
    ResObject getRecommendUserInfoByPhone(String phone);
}

