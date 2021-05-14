package com.drive.admin.repository;

import com.drive.admin.pojo.dto.RecommendManagerEditParam;
import com.drive.admin.pojo.dto.RecommendManagerPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 推广渠道经理 服务类
 *
 * @author xiaoguo
 */
public interface RecommendManagerRepository extends BaseRepository<RecommendManagerPageQueryParam, RecommendManagerEditParam>{


    /**
     * 添加渠道经理
     * @param recommendManagerEditParam
     * @return
     */
    ResObject saveRecommendManager(RecommendManagerEditParam recommendManagerEditParam);


    /**
     * 修改渠道经理
     * @param recommendManagerEditParam
     * @return
     */
    ResObject updateRecommendManager(RecommendManagerEditParam recommendManagerEditParam);
}

