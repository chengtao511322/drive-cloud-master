package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 活动项目设置
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ActivityProjectSettingPageQueryParam extends BasePageQueryParam {


	private String id;

	// 项目id 班型ID
	private String projectId;

	// 项目名称
	private String projectName;

	// 租户ID
	private String tenantId;

	// 活动提成金额
	private BigDecimal deductAmount;

	// 推广人员 获取佣金
	private BigDecimal promotionAmount;

	// 渠道经理获取佣金
	private BigDecimal channelManagerAmount;

	// 活动ID
	private String activityId;

}