package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台交易流水日志
 *
 * @author xiaoguo
 */
@Data
public class PayFlowLogVo {


	// 编号
	@Excel(name = "编号", width = 20)
	private String id;

	// 订单号
	@Excel(name = "订单号", width = 20)
	private String orderNo;

	// 交易类型(1;支付流水；2：退款流水 )
	@Excel(name = "交易类型(1;支付流水；2：退款流水 )", width = 20)
	private String tranType;

	// 支付类型(1：支付宝；2：微信) 
	@Excel(name = "支付类型(1：支付宝；2：微信) ", width = 20)
	private String payType;

	// 支付方账号
	@Excel(name = "支付方账号", width = 20)
	private String payAccount;

	// 订单应付金额
	@Excel(name = "订单应付金额", width = 20)
	private BigDecimal payableAmount;

	// 第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)
	@Excel(name = "第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)", width = 20)
	private String thirdOrderNo;

	// 第三方订金额
	@Excel(name = "第三方订金额", width = 20)
	private BigDecimal thirdOrderAmount;

	// 交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)
	@Excel(name = "交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)", width = 20)
	private String status;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 第三方返回时间
	@Excel(name = "第三方返回时间", width = 20)
	private LocalDateTime thirdReturnTime;

	// 提交参数
	@Excel(name = "提交参数", width = 20)
	private String commitParameter;

	// 返回参数
	@Excel(name = "返回参数", width = 20)
	private String returnParameter;

	// 支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）
	@Excel(name = "支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）", width = 20)
	private LocalDateTime payTime;

	// 买家支付宝账号（用于在取消扣款时，转账使用）
	@Excel(name = "买家支付宝账号（用于在取消扣款时，转账使用）", width = 20)
	private String buyerLogonId;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}