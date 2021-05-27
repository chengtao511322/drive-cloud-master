package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import com.drive.admin.job.JobManager;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


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

	//执行状态
	private String executeState;

	//下一次执行时间
	private Date nextFireTime;

	public void setStTaskId(String taskId) {
		this.stTaskId =taskId;
		//获取任务调度器scheduler
		Scheduler scheduler = JobManager.getScheduler();
		//获取jobKey name就是taskId
		JobKey jobKey = JobManager.getJobKey(taskId);
		try {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			if(CollectionUtils.isNotEmpty(triggers)){
				String isExecute = scheduler.getTriggerState(triggers.get(0).getKey()).toString();
				if (("NORMAL").equals(isExecute)) {
					this.executeState = "执行中";// 已执行
					this.nextFireTime = triggers.get(0).getNextFireTime();// 已执行
				} else {
					this.executeState = "等待或异常";// 等待或异常
				}
			}else {
				this.executeState = "未执行";
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void setNextFireTime() {
		//获取任务调度器scheduler
		Scheduler scheduler = JobManager.getScheduler();
		//获取jobKey name就是taskId
		JobKey jobKey = JobManager.getJobKey(this.stTaskId);
		try {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			if(CollectionUtils.isNotEmpty(triggers)){
				String isExecute = scheduler.getTriggerState(triggers.get(0).getKey()).toString();
				if (("NORMAL").equals(isExecute)) {

				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}
}