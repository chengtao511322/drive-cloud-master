package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 系统任务表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("sys_task")
public class SysTaskEntity extends BaseEntity {


	// 任务编号
	@TableId(type = IdType.INPUT)
	private String stTaskId;

	// 任务名称
	private String stTaskName;

	// 任务类
	private String stJobClass;

	// CRON表达式
	private String stCronExpression;

	// 状态
	private String stState;

	// 记入日志类型
	private String stInputLogType;

	// 备注
	private String stRemark;

	// 创建人
	private String createUser;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 修改人
	private String updateUser;

}