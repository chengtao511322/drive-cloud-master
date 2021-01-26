package com.drive.marketing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.marketing.pojo.entity.ActivityCouponRelationEntity;

import java.util.List;

/**
 *
 * 活动优惠券关联表 服务类
 *
 * @author xiaoguo
 */
public interface ActivityCouponRelationService extends IService<ActivityCouponRelationEntity>{

    /**
     * 批量删除
     * @param activityCouponRelationList
     * @return
     */
    int removeBatch(List<ActivityCouponRelationEntity> activityCouponRelationList);

    /**
     * 根据条件删除优惠券 活动关联表
     * {
     *     activityId:活动ID
     *     couponId:优惠券ID
     * }
     * @param activityCouponRelationEditParam
     * @return
     */
    Boolean deleteByCondition(ActivityCouponRelationEntity activityCouponRelationEntity);

    /**
     * 通过活动ID删除数据
     * @param activityId
     * @return
     */
    Boolean deleteByActivityId(String activityId);

}

