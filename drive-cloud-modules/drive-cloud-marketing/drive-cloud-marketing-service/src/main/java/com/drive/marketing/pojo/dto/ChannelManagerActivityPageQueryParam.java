package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 渠道经理 可推广表配置
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ChannelManagerActivityPageQueryParam extends BasePageQueryParam {


	private String channelManagerId;

	// 活动ID
	private String activityId;

	// 冗余 活动名称
	private String activityName;
	// 用户ID
	private String userId;
	// 创建时间
	private String createTime;
	// 版型ID
	private String projectId;
	// 活动投入金额
	private BigDecimal deductAmount;
	// 推广商所得
	private BigDecimal promotionAmount;
	// 渠道经理所得
	private BigDecimal channelManagerAmount;

	// 班型名称
	private String projectName;

	// 产品价格
	private BigDecimal projectAmount;

	// 状态  0 正常 1 禁言
	private String status;
	// 删除状态 0正常 1已经删除
	private String isDelete;

	private String id;

	private String promotionUserId;

	private String tenantId;

	private List<ActivityProjectSettingEditParam> activityProjectSettingList;

	// 推广类型 1：渠道经理 2：推广商
	private Integer promotionType;

}