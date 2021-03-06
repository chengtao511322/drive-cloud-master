package com.drive.admin.repository;

import com.drive.admin.pojo.dto.RecommendUserEditParam;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
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

    /**
     * 批量获取数据
     * @param ids
     * @return
     */
    ResObject<Map<String, RecommendUserVo>> batchRecommendUserVo(String[] ids);
}

