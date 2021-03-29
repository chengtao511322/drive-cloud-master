package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台账务流水
 *
 * @author xiaoguo
 */
@Data
public class AccountFlowEditParam {


    // id
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private String id;

    // 订单号
    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    // 流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))
    @NotBlank(message = "流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))不能为空")
    @ApiModelProperty(value = "流水类型(1;支付流水；2：退款流水 ; 3-取消收入流水;4-取消扣款流水;5-优惠卷扣款流水,6-平台支付流水(平台支付出去的费用))")
    private String flowType;

    // 支付类型(1：支付宝；2：微信)
    @NotBlank(message = "支付类型(1：支付宝；2：微信)不能为空")
    @ApiModelProperty(value = "支付类型(1：支付宝；2：微信)")
    private String payType;

    // 订单金额
    @NotBlank(message = "订单金额不能为空")
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    // 实际支付金额
    @NotBlank(message = "实际支付金额不能为空")
    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal actualPayAmount;

    // 退款金额
    @NotBlank(message = "退款金额不能为空")
    @ApiModelProperty(value = "退款金额")
    private BigDecimal drawbackAmount;

    // 支付时间
    @NotBlank(message = "支付时间不能为空")
    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 是否结算（0-否 ，1-是）
    @NotBlank(message = "是否结算（0-否 ，1-是）不能为空")
    @ApiModelProperty(value = "是否结算（0-否 ，1-是）")
    private String isSettlement;

    // 退款类型（0：全额退款 1：部分退款）
    @NotBlank(message = "退款类型（0：全额退款 1：部分退款）不能为空")
    @ApiModelProperty(value = "退款类型（0：全额退款 1：部分退款）")
    private String refundType;

    // 退款时间
    @NotBlank(message = "退款时间不能为空")
    @ApiModelProperty(value = "退款时间")
    private LocalDateTime refundTime;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}