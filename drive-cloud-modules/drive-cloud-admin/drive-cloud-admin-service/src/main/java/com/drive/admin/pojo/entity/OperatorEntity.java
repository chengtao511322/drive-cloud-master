package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 运营商基础信息
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_operator")
public class OperatorEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 运营商名称
	private String name;

	// 运营商级别(1-省，2-市，3-区(县))
	private Integer level;

	// 平台提成百分比
	private BigDecimal platformChargePercent;

	// 是否删除
	@TableLogic
   	@TableField(value="is_delete")
	private String isDelete;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 使用状态（1：正常；2：停用）
	private String status;

	// 父级代理商id
	private String parentId;

	// 分配课时绩效比例
	private BigDecimal hourPerformanceRatio;

	// 其他benaid
	private String channelBeanId;

}