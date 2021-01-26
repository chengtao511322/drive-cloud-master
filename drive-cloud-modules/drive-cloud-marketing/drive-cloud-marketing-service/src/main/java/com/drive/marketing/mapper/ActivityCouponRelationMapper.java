package com.drive.marketing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.marketing.pojo.entity.ActivityCouponRelationEntity;

import java.util.List;

/**
 * 活动优惠券关联表 Mapper 接口
 *
 * @author xiaoguo
 */
public interface ActivityCouponRelationMapper extends BaseMapper<ActivityCouponRelationEntity> {

    /**
     * 批量删除
     * @param activityCouponRelationList
     * @return
     */
    int removeBatch(List<ActivityCouponRelationEntity> activityCouponRelationList);

    int deleteByCondition(ActivityCouponRelationEntity activityCouponRelationEntity);

    /**
     * 通过活动ID 删除数据
     *
     * @param activityId
     * @return
     */
    int deleteByActivityId(String activityId);
}

