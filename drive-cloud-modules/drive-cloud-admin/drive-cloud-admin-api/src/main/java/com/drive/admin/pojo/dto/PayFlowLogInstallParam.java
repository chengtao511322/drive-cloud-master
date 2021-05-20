package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 平台交易流水日志
 *
 * @author xiaoguo
 */
@Data
public class PayFlowLogInstallParam {


    // 编号
    @NotBlank(message = "编号不能为空")
    @ApiModelProperty(value = "编号")
    private String id;

    // 订单号
    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    // 交易类型(1;支付流水；2：退款流水 )
    @NotBlank(message = "交易类型(1;支付流水；2：退款流水 )不能为空")
    @ApiModelProperty(value = "交易类型(1;支付流水；2：退款流水 )")
    private String tranType;

    // 支付类型(1：支付宝；2：微信) 
    @NotBlank(message = "支付类型(1：支付宝；2：微信) 不能为空")
    @ApiModelProperty(value = "支付类型(1：支付宝；2：微信) ")
    private String payType;

    // 支付方账号
    @NotBlank(message = "支付方账号不能为空")
    @ApiModelProperty(value = "支付方账号")
    private String payAccount;

    // 订单应付金额
    @NotBlank(message = "订单应付金额不能为空")
    @ApiModelProperty(value = "订单应付金额")
    private BigDecimal payableAmount;

    // 第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)
    @NotBlank(message = "第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)不能为空")
    @ApiModelProperty(value = "第三方订单号(支付时为，第三方支付订单号。退款时为：退款唯一订单号,转账退款也归为退款)")
    private String thirdOrderNo;

    // 第三方订金额
    @NotBlank(message = "第三方订金额不能为空")
    @ApiModelProperty(value = "第三方订金额")
    private BigDecimal thirdOrderAmount;

    // 交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)
    @NotBlank(message = "交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)不能为空")
    @ApiModelProperty(value = "交易状态（1-待付款，2-付款处理中，3-付款成功	4-付款失败，5-退款处理中，6-退款成功，7-交易结束不可退款，8-退款失败)")
    private String status;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @NotBlank(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 第三方返回时间
    @NotBlank(message = "第三方返回时间不能为空")
    @ApiModelProperty(value = "第三方返回时间")
    private LocalDateTime thirdReturnTime;

    // 提交参数
    @NotBlank(message = "提交参数不能为空")
    @ApiModelProperty(value = "提交参数")
    private String commitParameter;

    // 返回参数
    @NotBlank(message = "返回参数不能为空")
    @ApiModelProperty(value = "返回参数")
    private String returnParameter;

    // 支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）
    @NotBlank(message = "支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）不能为空")
    @ApiModelProperty(value = "支付时间（主要用于记录：学车报名时的支付时间，因为学车报名就流水是在报名完成时才生成）")
    private LocalDateTime payTime;

    // 买家支付宝账号（用于在取消扣款时，转账使用）
    @NotBlank(message = "买家支付宝账号（用于在取消扣款时，转账使用）不能为空")
    @ApiModelProperty(value = "买家支付宝账号（用于在取消扣款时，转账使用）")
    private String buyerLogonId;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}