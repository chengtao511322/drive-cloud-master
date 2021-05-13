package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 流程信息管理
 *
 * @author xiaoguo
 */
@Data
public class DriveFlowVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 标题
	@Excel(name = "标题", width = 20)
	private String title;

	@Excel(name = "", width = 20)
	private String context;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 删除状态 0未删除  1 ：删除
	@Excel(name = "删除状态 0未删除  1 ：删除", width = 20)
	private Integer isDelete;

	// 启用状态 0  未启用 1 已经启用
	@Excel(name = "启用状态 0  未启用 1 已经启用", width = 20)
	private Integer status;

	// 栏目ID
	private String channelId;
	@Excel(name = "栏目菜单名称", width = 20)
	private String channelName;

	// 排序
	@Excel(name = "排序", width = 20)
	private Integer sort;

	// 添加者
	@Excel(name = "添加者", width = 20)
	private String addUser;

	// 运营位置 ，存放省市区编码，多个用逗号隔开
	@Excel(name = "运营位置 ，存放省市区编码，多个用逗号隔开", width = 20)
	private String operatingPosition;

	// 租户ID
	@Excel(name = "租户ID", width = 20)
	private String tenantId;


	// 类型 1 学车流程 2 平台承诺
	private Integer type;

}