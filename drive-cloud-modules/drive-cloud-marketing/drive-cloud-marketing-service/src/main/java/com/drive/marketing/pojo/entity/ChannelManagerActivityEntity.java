package com.drive.marketing.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 渠道经理 可推广表配置
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("mark_channel_manager_activity")
public class ChannelManagerActivityEntity implements Serializable {


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
	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private String isDelete;

	@TableId(type= IdType.ID_WORKER)
	private String id;

	private String promotionUserId;

	@TableField(exist = false)
	private String tenantId;

	// 推广类型 1：渠道经理 2：推广商
	private Integer promotionType;


}