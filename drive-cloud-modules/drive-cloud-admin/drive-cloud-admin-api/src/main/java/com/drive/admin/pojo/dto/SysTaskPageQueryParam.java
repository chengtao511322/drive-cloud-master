package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 系统任务表
 *
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SysTaskPageQueryParam extends BasePageQueryParam {


	// 任务编号
	@ApiModelProperty(value = "任务编号")
	private String stTaskId;
	// 模糊查询字段

	// 任务名称
	@ApiModelProperty(value = "任务名称")
	private String stTaskName;
	// 模糊查询字段

	// 任务类
	@ApiModelProperty(value = "任务类")
	private String stJobClass;
	// 模糊查询字段

	// CRON表达式
	@ApiModelProperty(value = "CRON表达式")
	private String stCronExpression;
	// 模糊查询字段

	// 状态
	@ApiModelProperty(value = "启动时是否加载状态 0-否 1-是")
	private String stState;
	// 模糊查询字段

	// 记入日志类型
	@ApiModelProperty(value = "记入日志类型")
	private String stInputLogType;
	// 模糊查询字段

	// 备注
	@ApiModelProperty(value = "备注")
	private String stRemark;
	// 模糊查询字段

	// 创建人
	@ApiModelProperty(value = "创建人")
	private String createUser;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 修改人
	@ApiModelProperty(value = "修改人")
	private String updateUser;
	// 模糊查询字段
	//private String vagueNameSearch

}