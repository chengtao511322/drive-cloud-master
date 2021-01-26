package com.drive.marketing.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("mark_activity_promotion")
public class ActivityPromotionEntity implements Serializable {


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

	// 推广商获得金额
	private BigDecimal promotionAmount;

	private String projectId;

	// 用户ID
	private String userId;
	// 投入金额
	private BigDecimal deductAmount;
	// 渠道经理获得金额
	private BigDecimal channelManagerAmount;
	// 创建时间
	private String createTime;
	// 修改时间
	private String updateTime;

	// 班型名称
	private String projectName;
	// 状态  0 正常 1 禁言
	private String status;
	// 删除状态 0正常 1已经删除
	private String isDelete;

	private String id;

	// 渠道经理ID
	private String channelManagerId;


}