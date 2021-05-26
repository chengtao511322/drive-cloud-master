package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 
 *
 * @author chentao
 */
@Data
public class WalletSettlementSummaryVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 钱包用户id
	@Excel(name = "钱包用户id", width = 20)
	private String userId;

	// 清算总金额
	@Excel(name = "清算总金额", width = 20)
	private BigDecimal settleAccountsSum;

	// 清算申请时间
	@Excel(name = "清算申请时间", width = 20)
	private LocalDateTime settleAccountsApplyTime;

	// 清算完成时间
	@Excel(name = "清算完成时间", width = 20)
	private LocalDateTime settleAccountsCompleteTime;

	// 审核通过时间
	@Excel(name = "审核通过时间", width = 20)
	private LocalDateTime examineTime;

	// 清算笔数
	@Excel(name = "清算笔数", width = 20)
	private Integer settleAccountsNumber;

	// 清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)
	@Excel(name = "清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)", width = 20)
	private String status;

	// 清算方式(1-支付宝 ,2-微信,3-银行卡)
	@Excel(name = "清算方式(1-支付宝 ,2-微信,3-银行卡)", width = 20)
	private String settleType;

	// 转账唯一订单号
	@Excel(name = "转账唯一订单号", width = 20)
	private String outBizNo;

	// 备注
	@Excel(name = "备注", width = 20)
	private String remarks;

	// 提现账号
	@Excel(name = "提现账号", width = 20)
	private String submitAccount;

	// 提现真实姓名
	@Excel(name = "提现真实姓名", width = 20)
	private String submitRealName;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 驳回时间
	@Excel(name = "驳回时间", width = 20)
	private LocalDateTime rejectTime;

	// 开户行
	@Excel(name = "开户行", width = 20)
	private String openAccountBank;

}