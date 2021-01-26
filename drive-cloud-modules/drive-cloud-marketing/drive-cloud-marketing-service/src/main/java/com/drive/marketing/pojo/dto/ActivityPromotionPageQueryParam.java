package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ActivityPromotionPageQueryParam extends BasePageQueryParam {


	// 推广人员用户ID
	private String promotionUserId;

	// 推广人员类型
	private Integer promotionType;

	// 报名百分比
	private BigDecimal applyPre;

	// 推广手机号
	private String promotionPhone;

	// 推广人员姓名
	private String promotionUserName;

	// 活动ID
	private String activityId;

	// 渠道经理ID
	private String channelManagerid;

	// 推广商获得金额
	private BigDecimal promotionAmount;

	private String projectId;

}