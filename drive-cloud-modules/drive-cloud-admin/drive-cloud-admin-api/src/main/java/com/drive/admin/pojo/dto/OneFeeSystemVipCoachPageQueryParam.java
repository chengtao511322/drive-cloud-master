package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 一费制vip教练
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OneFeeSystemVipCoachPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 教练id
	@ApiModelProperty(value = "教练id")
	private String coachId;
	// 模糊查询字段

	// 入驻时间
	@ApiModelProperty(value = "入驻时间")
	private LocalDateTime joinTime;
	// 模糊查询字段

	// 状态（正常，停用）
	@ApiModelProperty(value = "状态（正常，停用）")
	private String stutas;
	// 模糊查询字段

	// 备注(主要存储，停用原因)
	@ApiModelProperty(value = "备注(主要存储，停用原因)")
	private String remarks;
	// 模糊查询字段

	// 是否删除
	@ApiModelProperty(value = "是否删除")
	private String isDelete;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段

	// 预收入单价
	@ApiModelProperty(value = "预收入单价")
	private BigDecimal expectIncomePrice;
	// 模糊查询字段

	// 教练提成百分比
	@ApiModelProperty(value = "教练提成百分比")
	private BigDecimal coachChargePercent;
	// 模糊查询字段

	// 公车提成百分比（驾校收入）
	@ApiModelProperty(value = "公车提成百分比（驾校收入）")
	private BigDecimal carChargePercent;
	// 模糊查询字段
	//private String vagueNameSearch

}