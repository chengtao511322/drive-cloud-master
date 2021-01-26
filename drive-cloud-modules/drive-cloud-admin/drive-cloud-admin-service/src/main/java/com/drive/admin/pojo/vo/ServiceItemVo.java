package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 服务项目表
 *
 * @author JoyoDuan
 */
@Data
public class ServiceItemVo {


	// 主键id
	@Excel(name = "主键id", width = 20)
	private String id;

	// 服务名称
	@Excel(name = "服务名称", width = 20)
	private String name;

	// 服务类型（报名、科一、科二、科三、科四）
	@Excel(name = "服务类型（报名、科一、科二、科三、科四）", width = 20)
	private String serviceType;

	// 是否删除（1、是，0、否）
	@Excel(name = "是否删除（1、是，0、否）", width = 20)
	private String isDelete;

	// 状态（1、正常，2、停用）
	@Excel(name = "状态（1、正常，2、停用）", width = 20)
	private String status;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 服务项描述，表述该服务项的具体含义 。 例如：体检面签费是什么？
	private String serviceItemDescribe;

}