package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 
 *
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class WalletSettlementDetailPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 清算总表id
	@ApiModelProperty(value = "清算总表id")
	private String settleAccountsId;
	// 模糊查询字段

	// 钱包明细id
	@ApiModelProperty(value = "钱包明细id")
	private String walletDetailId;
	// 模糊查询字段

	// 清算金额
	@ApiModelProperty(value = "清算金额")
	private BigDecimal settleAccountsMoney;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	//private String vagueNameSearch

}