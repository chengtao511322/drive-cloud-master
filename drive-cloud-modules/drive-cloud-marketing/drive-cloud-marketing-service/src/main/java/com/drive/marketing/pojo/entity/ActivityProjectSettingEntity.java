package com.drive.marketing.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
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
@TableName("activity_project_setting")
public class ActivityProjectSettingEntity extends BaseEntity {


	private String id;

	// 项目id 班型ID
	private String projectId;

	// 项目名称
	private String projectName;
	// 产品价格
	private BigDecimal projectAmount;

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

	// 删除状态
	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private String isDelete;

	private String status;

}