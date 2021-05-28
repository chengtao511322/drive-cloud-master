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
 * 平台交易流水日志
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PayFlowLogPageQueryParam extends BasePageQueryParam {


	// 编号
	@ApiModelProperty(value = "编号")
	private String id;
	// 模糊查询字段

	// 订单号
	@ApiModelProperty(value = "订单号")
	private String orderNo;
	// 模糊查询字段

	// 交易类型(1;支付流水；2：退款流水 )
	@ApiModelProperty(value = "交易类型(1;支付流水；2：退款流水 )")
	private String tranType;
	// 模糊查询字段

	// 支付类型(1：支付宝；2：微信) 
	@ApiModelProperty(value = "支付类型(1：支付宝；2：微信) ")
	private String payType;
	// 模糊查询字段

	// 支付方账号
	@ApiModelProperty(value = "支付方账号")
	private String payAccount;
	// 模糊查询字段

	// 订单应付金额
	@ApiModelProperty(value = "订单应付金额")
	private BigDecimal payableAmount;
	// 模糊查询字段

	// 第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)
	@ApiModelProperty(value = "第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)")
	private String thirdOrderNo;
	// 模糊查询字段

	// 第三方订金额
	@ApiModelProperty(value = "第三方订金额")
	private BigDecimal thirdOrderAmount;
	// 模糊查询字段

	// 交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)
	@ApiModelProperty(value = "交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)")
	private String status;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 第三方返回时间
	@ApiModelProperty(value = "第三方返回时间")
	private LocalDateTime thirdReturnTime;
	// 模糊查询字段

	// 提交参数
	@ApiModelProperty(value = "提交参数")
	private String commitParameter;
	// 模糊查询字段

	// 返回参数
	@ApiModelProperty(value = "返回参数")
	private String returnParameter;
	// 模糊查询字段

	// 支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）
	@ApiModelProperty(value = "支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）")
	private LocalDateTime payTime;
	// 模糊查询字段

	// 买家支付宝账号（用于在取消扣款时，转账使用）
	@ApiModelProperty(value = "买家支付宝账号（用于在取消扣款时，转账使用）")
	private String buyerLogonId;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	private String vagueOrderNoSearch;
	private String vagueThirdOrderNoSearch;
	// 创建时间
	private String[] createArrSearch = new String[2];
	// 支付时间
	private String[] payTimeArrSearch = new String[2];

}