package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class WalletSettlementSummaryPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 钱包用户id
	@ApiModelProperty(value = "钱包用户id")
	private String userId;
	// 模糊查询字段

	// 清算总金额
	@ApiModelProperty(value = "清算总金额")
	private BigDecimal settleAccountsSum;
	// 模糊查询字段

	// 清算申请时间
	@ApiModelProperty(value = "清算申请时间")
	private LocalDateTime settleAccountsApplyTime;
	// 模糊查询字段

	// 清算完成时间
	@ApiModelProperty(value = "清算完成时间")
	private LocalDateTime settleAccountsCompleteTime;
	// 模糊查询字段

	// 审核通过时间
	@ApiModelProperty(value = "审核通过时间")
	private LocalDateTime examineTime;
	// 模糊查询字段

	// 清算笔数
	@ApiModelProperty(value = "清算笔数")
	private Integer settleAccountsNumber;
	// 模糊查询字段

	// 清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)
	@ApiModelProperty(value = "清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)")
	private String status;
	// 模糊查询字段

	// 清算方式(1-支付宝 ,2-微信,3-银行卡)
	@ApiModelProperty(value = "清算方式(1-支付宝 ,2-微信,3-银行卡)")
	private String settleType;
	// 模糊查询字段

	// 转账唯一订单号
	@ApiModelProperty(value = "转账唯一订单号")
	private String outBizNo;
	// 模糊查询字段

	// 备注
	@ApiModelProperty(value = "备注")
	private String remarks;
	// 模糊查询字段

	// 提现账号
	@ApiModelProperty(value = "提现账号")
	private String submitAccount;
	// 模糊查询字段

	// 提现真实姓名
	@ApiModelProperty(value = "提现真实姓名")
	private String submitRealName;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段

	// 驳回时间
	@ApiModelProperty(value = "驳回时间")
	private LocalDateTime rejectTime;
	// 模糊查询字段

	// 开户行
	@ApiModelProperty(value = "开户行")
	private String openAccountBank;
	// 模糊查询字段
	//private String vagueNameSearch

}