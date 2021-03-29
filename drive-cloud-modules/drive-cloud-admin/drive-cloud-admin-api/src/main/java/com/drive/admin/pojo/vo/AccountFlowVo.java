package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台账务流水
 *
 * @author xiaoguo
 */
@Data
public class AccountFlowVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 订单号
	@Excel(name = "订单号", width = 20)
	private String orderNo;

	// 流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))
	@Excel(name = "流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))", width = 20)
	private String flowType;

	// 支付类型(1：支付宝；2：微信)
	@Excel(name = "支付类型(1：支付宝；2：微信)", width = 20)
	private String payType;

	// 订单金额
	@Excel(name = "订单金额", width = 20)
	private BigDecimal orderAmount;

	// 实际支付金额
	@Excel(name = "实际支付金额", width = 20)
	private BigDecimal actualPayAmount;

	// 退款金额
	@Excel(name = "退款金额", width = 20)
	private BigDecimal drawbackAmount;

	// 支付时间
	@Excel(name = "支付时间", width = 20)
	private LocalDateTime payTime;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 是否结算（0-否 ，1-是）
	@Excel(name = "是否结算（0-否 ，1-是）", width = 20)
	private String isSettlement;

	// 退款类型（0：全额退款 1：部分退款）
	@Excel(name = "退款类型（0：全额退款 1：部分退款）", width = 20)
	private String refundType;

	// 退款时间
	@Excel(name = "退款时间", width = 20)
	private LocalDateTime refundTime;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}