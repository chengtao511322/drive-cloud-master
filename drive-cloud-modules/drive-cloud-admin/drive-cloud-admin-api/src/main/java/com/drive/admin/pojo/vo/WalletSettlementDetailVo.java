package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 
 *
 * @author chentao
 */
@Data
public class WalletSettlementDetailVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 清算总表id
	@Excel(name = "清算总表id", width = 20)
	private String settleAccountsId;

	// 钱包明细id
	@Excel(name = "钱包明细id", width = 20)
	private String walletDetailId;

	// 清算金额
	@Excel(name = "清算金额", width = 20)
	private BigDecimal settleAccountsMoney;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}