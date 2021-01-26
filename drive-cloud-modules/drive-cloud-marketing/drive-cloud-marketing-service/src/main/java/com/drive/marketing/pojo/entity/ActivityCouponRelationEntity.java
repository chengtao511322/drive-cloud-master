package com.drive.marketing.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 活动优惠券关联表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("activity_coupon_relation")
public class ActivityCouponRelationEntity implements Serializable {


	// id
	@TableId(type= IdType.ID_WORKER)
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