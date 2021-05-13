package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 提成设置表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class DeductSettingPageQueryParam extends BasePageQueryParam {


	@ApiModelProperty(value = "")
	private String name;
	// 模糊查询字段

	// 用户类型 1-个人，2-商铺,3 企业类型 4  学校类型
	@ApiModelProperty(value = "用户类型 1-个人，2-商铺,3 企业类型 4  学校类型")
	private Integer userType;
	// 模糊查询字段

	// 经理课时提成比例
	@ApiModelProperty(value = "经理课时提成比例")
	private BigDecimal managerHourProportion;
	// 模糊查询字段

	// 经理报名提成金额
	@ApiModelProperty(value = "经理报名提成金额")
	private BigDecimal managerApplyAmount;
	// 模糊查询字段

	// 代理商课时提成比
	@ApiModelProperty(value = "代理商课时提成比")
	private BigDecimal agencyHourProportion;
	// 模糊查询字段

	// 代理商报名提成金额
	@ApiModelProperty(value = "代理商报名提成金额")
	private BigDecimal agencyApplyAmount;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段

	// 渠道经理ID
	@ApiModelProperty(value = "渠道经理ID")
	private String recommendManagerId;
	// 模糊查询字段

	// 代理商ID
	@ApiModelProperty(value = "代理商ID")
	private String recommendAgencyId;
	// 模糊查询字段
	//private String vagueNameSearch

}