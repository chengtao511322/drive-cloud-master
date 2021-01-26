package com.drive.marketing.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 活动参加记录表
 *
 * @author xiaoguo
 */
@Data
public class ActivityApplyVo {


	@Excel(name = "", width = 20)
	private String id;

	// 用户名称
	@Excel(name = "用户名称", width = 20)
	private String userName;

	// 手机号
	@Excel(name = "手机号", width = 20)
	private String phone;

	// 活动名称
	@Excel(name = "活动名称", width = 20)
	private String activityName;

	@Excel(name = "", width = 20)
	private String userId;

	// 活动ID
	@Excel(name = "活动ID", width = 20)
	private String activityId;

	@Excel(name = "", width = 20)
	private LocalDateTime applyTime;

	// 创建者
	@Excel(name = "创建者", width = 20)
	private String addUser;

	@Excel(name = "", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 启用状态 0  未启用  1 已经启用
	@Excel(name = "启用状态 0  未启用  1 已经启用", width = 20)
	private Integer status;

	// 删除状态 0 未删除1删除
	@Excel(name = "删除状态 0 未删除1删除", width = 20)
	private Integer isDelete;

	// 运营位置 ，存放省市区编码，多个用逗号隔开
	@Excel(name = "运营位置 ，存放省市区编码，多个用逗号隔开", width = 20)
	private String operatingPosition;

	@Excel(name = "", width = 20)
	private String operator;

	// 租户ID
	@Excel(name = "租户ID", width = 20)
	private String tenantId;

	@Excel(name = "", width = 20)
	private String promoteUserId;

	@Excel(name = "", width = 20)
	private String projectId;

}