package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 字典表
 *
 * @author chentao
 */
@Data
public class CodeVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String codeId;

	// 分类
	@Excel(name = "分类", width = 20)
	private String category;

	// 值
	@Excel(name = "值", width = 20)
	private String codeValue;

	// 显示项
	@Excel(name = "显示项", width = 20)
	private String disText;

	// 标签
	@Excel(name = "标签", width = 20)
	private String tags;

	// 排序
	@Excel(name = "排序", width = 20)
	private String odBy;

	// 备注
	@Excel(name = "备注", width = 20)
	private String remarks;

	// 父项
	@Excel(name = "父项", width = 20)
	private String supCodeId;

	// 创建人
	@Excel(name = "创建人", width = 20)
	private String createUser;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新人
	@Excel(name = "更新人", width = 20)
	private String updateUser;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

}