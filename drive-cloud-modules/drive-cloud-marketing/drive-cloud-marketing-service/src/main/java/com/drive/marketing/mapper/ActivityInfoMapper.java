package com.drive.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.vo.ActivityCouponRelationVo;

import java.util.List;

public interface ActivityInfoMapper extends BaseMapper<ActivityInfoEntity> {

    /**
     * 通过活动ID 查询关联优惠券信息
     * @param activityId
     * @return
     */
    List<ActivityCouponRelationVo> getActivityCouponRelation(String activityId);

    /**
     * 条件查询优惠券
     * @param activityCouponRelationEditParam
     * @return
     */
    ActivityCouponRelationVo getActivityCouponRelationByCondition(ActivityCouponRelationEditParam activityCouponRelationEditParam);
}