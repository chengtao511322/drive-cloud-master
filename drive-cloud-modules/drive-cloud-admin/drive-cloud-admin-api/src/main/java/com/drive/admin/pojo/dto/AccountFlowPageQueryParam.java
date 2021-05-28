package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 平台账务流水
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AccountFlowPageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 订单号
	@ApiModelProperty(value = "订单号")
	private String orderNo;
	// 模糊查询字段

	// 流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))
	@ApiModelProperty(value = "流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))")
	private String flowType;
	// 模糊查询字段

	// 支付类型(1：支付宝；2：微信)
	@ApiModelProperty(value = "支付类型(1：支付宝；2：微信)")
	private String payType;
	// 模糊查询字段

	// 订单金额
	@ApiModelProperty(value = "订单金额")
	private BigDecimal orderAmount;
	// 模糊查询字段

	// 实际支付金额
	@ApiModelProperty(value = "实际支付金额")
	private BigDecimal actualPayAmount;
	// 模糊查询字段

	// 退款金额
	@ApiModelProperty(value = "退款金额")
	private BigDecimal drawbackAmount;
	// 模糊查询字段

	// 支付时间
	@ApiModelProperty(value = "支付时间")
	private LocalDateTime payTime;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 是否结算（0-否 ，1-是）
	@ApiModelProperty(value = "是否结算（0-否 ，1-是）")
	private String isSettlement;
	// 模糊查询字段

	// 退款类型（0：全额退款 1：部分退款）
	@ApiModelProperty(value = "退款类型（0：全额退款 1：部分退款）")
	private String refundType;
	// 模糊查询字段

	// 退款时间
	@ApiModelProperty(value = "退款时间")
	private LocalDateTime refundTime;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	private String vagueOrderNoSearch;

	// 创建时间
	private String[] createArrSearch = new String[2];
	// 支付时间
	private String[] payTimeArrSearch = new String[2];

}