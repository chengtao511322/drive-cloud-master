package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CouponGetPageQueryParam extends BasePageQueryParam {


	// id
	private String id;


	// 用户名称
	private String userName;

	private String activityId;

	// 用户手机号
	private String phone;

	// 用户ID
	private String userId;

	// 优惠券ID
	private String couponId;

	// 优惠券使用状态:1 未领取  2 已经领取 3 已经使用 4：优惠券过期  
	private String status;

	// 领取时间
	private String getTime;

	// 修改时间
	private String updateTime;

	// 优惠券码
	private String couponCode;
	//
	private String couponName;

	// 获取类型：0->后台赠送；1->主动获取
	private String getType;

	// 使用时间
	private String useTime;

	// 有效期开始日
	private String periodTimeStart;

	// 有效期内  结束
	private String periodTimeEnd;

	private String operatingPosition;

	// 来源 ID  （主要用于来源活动ID）
	private String source;

	// 租户ID
	private String tenantId;

	private LocalDateTime createTime;

	// 推广ID
	private String promoteUserId;

	private String useBeginTime;

	private String useEndTime;

}