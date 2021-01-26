package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 活动优惠券关联表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ActivityCouponRelationPageQueryParam extends BasePageQueryParam {


	// id
	private String id;

	// 优惠券ID
	private String couponId;

	// 活动id
	private String activityId;

	// 优惠券名称
	private String couponName;

}