package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 运营商参数设置表
 *
 * @author xiaoguo
 */
@Data
public class OperatorSettinngVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 编号(使用 00000 的形式来添加)
	@Excel(name = "编号(使用 00000 的形式来添加)", width = 20)
	private String number;

	// 编号对应值
	@Excel(name = "编号对应值", width = 20)
	private String numberValue;

	// 编号中文描述
	@Excel(name = "编号中文描述", width = 20)
	private String numberDescribe;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 状态(1-正常，2-停用)
	@Excel(name = "状态(1-正常，2-停用)", width = 20)
	private String status;

	// 是否删除（0-否，1-是）
	@Excel(name = "是否删除（0-否，1-是）", width = 20)
	private String isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

}