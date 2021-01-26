package com.drive.marketing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.marketing.pojo.dto.ActivityCouponRelationEditParam;
import com.drive.marketing.pojo.entity.ActivityInfoEntity;
import com.drive.marketing.pojo.vo.ActivityCouponRelationVo;

import java.util.List;

/**
 * 活动服务
 */
public interface IActivityInfoService extends IService<ActivityInfoEntity> {

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
