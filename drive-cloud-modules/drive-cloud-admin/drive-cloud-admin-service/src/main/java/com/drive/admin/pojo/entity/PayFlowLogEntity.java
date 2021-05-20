package com.drive.admin.pojo.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 平台交易流水日志
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_pay_flow_log")
public class PayFlowLogEntity extends BaseEntity {


	// 编号
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 订单号
	private String orderNo;

	// 交易类型(1;支付流水；2：退款流水 )
	private String tranType;

	// 支付类型(1：支付宝；2：微信) 
	private String payType;

	// 支付方账号
	private String payAccount;

	// 订单应付金额
	private BigDecimal payableAmount;

	// 第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)
	private String thirdOrderNo;

	// 第三方订金额
	private BigDecimal thirdOrderAmount;

	// 交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)
	private String status;

	// 第三方返回时间
	private LocalDateTime thirdReturnTime;

	// 提交参数
	private String commitParameter;

	// 返回参数
	private String returnParameter;

	// 支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）
	private LocalDateTime payTime;

	// 买家支付宝账号（用于在取消扣款时，转账使用）
	private String buyerLogonId;

	// 运营商id(数据权限标记)
	private String operatorId;

}