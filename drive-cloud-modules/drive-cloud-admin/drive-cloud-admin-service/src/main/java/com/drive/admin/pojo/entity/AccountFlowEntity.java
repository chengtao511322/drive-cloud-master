package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台账务流水
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_account_flow")
public class AccountFlowEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 订单号
	private String orderNo;

	// 流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))
	private String flowType;

	// 支付类型(1：支付宝；2：微信)
	private String payType;

	// 订单金额
	private BigDecimal orderAmount;

	// 实际支付金额
	private BigDecimal actualPayAmount;

	// 退款金额
	private BigDecimal drawbackAmount;

	// 支付时间
	private LocalDateTime payTime;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 是否结算（0-否 ，1-是）
	private String isSettlement;

	// 退款类型（0：全额退款 1：部分退款）
	private String refundType;

	// 退款时间
	private LocalDateTime refundTime;

	// 运营商id(数据权限标记)
	private String operatorId;

}