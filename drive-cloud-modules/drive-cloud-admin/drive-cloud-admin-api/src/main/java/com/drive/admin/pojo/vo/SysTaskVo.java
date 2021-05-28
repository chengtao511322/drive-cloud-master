package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 系统任务表
 *
 * @author chentao
 */
@Data
public class SysTaskVo {


	// 任务编号
	@Excel(name = "任务编号", width = 20)
	private String stTaskId;

	// 任务名称
	@Excel(name = "任务名称", width = 20)
	private String stTaskName;

	// 任务类
	@Excel(name = "任务类", width = 20)
	private String stJobClass;

	// CRON表达式
	@Excel(name = "CRON表达式", width = 20)
	private String stCronExpression;

	// 状态
	@Excel(name = "状态", width = 20)
	private String stState;

	// 记入日志类型
	@Excel(name = "记入日志类型", width = 20)
	private String stInputLogType;

	// 备注
	@Excel(name = "备注", width = 20)
	private String stRemark;

	// 创建人
	@Excel(name = "创建人", width = 20)
	private String createUser;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 修改人
	@Excel(name = "修改人", width = 20)
	private String updateUser;

}