package com.drive.marketing.pojo.dto;

import lombok.Data;


/**
 * 活动优惠券关联表
 *
 * @author xiaoguo
 */
@Data
public class ActivityCouponRelationEditParam {


    // id
    private String id;

    // 优惠券ID
    private String couponId;

    // 活动id
    private String activityId;

    // 产品ID
    private String projectId;

    // 优惠券名称
    private String couponName;


}