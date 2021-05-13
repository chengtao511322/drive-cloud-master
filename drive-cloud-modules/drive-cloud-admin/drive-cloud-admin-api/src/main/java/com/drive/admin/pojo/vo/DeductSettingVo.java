package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 提成设置表
 *
 * @author xiaoguo
 */
@Data
public class DeductSettingVo {


	@Excel(name = "", width = 20)
	private String name;

	// 用户类型 1-个人，2-商铺,3 企业类型 4  学校类型
	@Excel(name = "用户类型 1-个人，2-商铺,3 企业类型 4  学校类型", width = 20)
	private Integer userType;

	// 经理课时提成比例
	@Excel(name = "经理课时提成比例", width = 20)
	private BigDecimal managerHourProportion;

	// 经理报名提成金额
	@Excel(name = "经理报名提成金额", width = 20)
	private BigDecimal managerApplyAmount;

	// 代理商课时提成比
	@Excel(name = "代理商课时提成比", width = 20)
	private BigDecimal agencyHourProportion;

	// 代理商报名提成金额
	@Excel(name = "代理商报名提成金额", width = 20)
	private BigDecimal agencyApplyAmount;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 渠道经理ID
	@Excel(name = "渠道经理ID", width = 20)
	private String recommendManagerId;

	// 代理商ID
	@Excel(name = "代理商ID", width = 20)
	private String recommendAgencyId;

}