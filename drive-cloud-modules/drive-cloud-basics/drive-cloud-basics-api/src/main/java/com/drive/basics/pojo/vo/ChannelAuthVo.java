package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 菜单 按钮 用户拥有权限管理
 *
 * @author xiaoguo
 */
@Data
public class ChannelAuthVo {


	private String id;

	// 菜单ID
	@Excel(name = "菜单ID", width = 20)
	private String channelId;

	// 用户ID
	@Excel(name = "用户ID", width = 20)
	private String userId;

	// 菜单ID
	@Excel(name = "菜单ID", width = 20)
	private String tenantId;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 创建者
	@Excel(name = "创建者", width = 20)
	private String createUser;


	private String isDelete;

	private String status;

}