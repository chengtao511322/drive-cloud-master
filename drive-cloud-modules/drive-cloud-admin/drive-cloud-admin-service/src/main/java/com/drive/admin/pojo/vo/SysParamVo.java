package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 系统配置参数表
 *
 * @author chentao
 */
@Data
public class SysParamVo {


	// 参数枚举编号
	@Excel(name = "参数枚举编号", width = 20)
	private String prmEnumId;

	// 参数名称
	@Excel(name = "参数名称", width = 20)
	private String prmName;

	// 参数中文涵义
	@Excel(name = "参数中文涵义", width = 20)
	private String prmCnName;

	// 参数数值
	@Excel(name = "参数数值", width = 20)
	private String prmValue;

	// 参数备注
	@Excel(name = "参数备注", width = 20)
	private String prmRemark;

	// 参数状态，0-正常 1-停用
	@Excel(name = "参数状态，0-正常 1-停用", width = 20)
	private String prmStatus;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 创建人
	@Excel(name = "创建人", width = 20)
	private String createUser;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 修改人
	@Excel(name = "修改人", width = 20)
	private String updateUser;

}