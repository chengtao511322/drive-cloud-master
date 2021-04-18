package com.drive.pay.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 支付交易流水信息表
 *
 * @author xiaoguo
 */
@Data
public class MentTransactionEditParam {


    // id
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private String id;

    // 支付金额
    @NotBlank(message = "支付金额不能为空")
    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

    // 支付状态;0待支付1已经支付2支付超时3支付失败
    @NotBlank(message = "支付状态;0待支付1已经支付2支付超时3支付失败不能为空")
    @ApiModelProperty(value = "支付状态;0待支付1已经支付2支付超时3支付失败")
    private Long paymentStatus;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String userId;

    // 订单号
    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    // 乐观锁
    @NotBlank(message = "乐观锁不能为空")
    @ApiModelProperty(value = "乐观锁")
    private Integer revision;

    // 第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用
    @NotBlank(message = "第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用不能为空")
    @ApiModelProperty(value = "第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用")
    private String partyPayId;

    // 使用雪花算法生产 支付系统 支付id
    @NotBlank(message = "使用雪花算法生产 支付系统 支付id不能为空")
    @ApiModelProperty(value = "使用雪花算法生产 支付系统 支付id")
    private String paymentId;

    // 创建人
    @NotBlank(message = "创建人不能为空")
    @ApiModelProperty(value = "创建人")
    private String createUser;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 修改时间
    @NotBlank(message = "修改时间不能为空")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    // 修改者
    @NotBlank(message = "修改者不能为空")
    @ApiModelProperty(value = "修改者")
    private String updateUser;

    // 支付渠道
    @NotBlank(message = "支付渠道不能为空")
    @ApiModelProperty(value = "支付渠道")
    private String paymentChannel;

    // 订单金额
    @NotBlank(message = "订单金额不能为空")
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    // body
    @NotBlank(message = "body不能为空")
    @ApiModelProperty(value = "body")
    private String body;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String subject;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String payParams;

    // 支付时间
    @NotBlank(message = "支付时间不能为空")
    @ApiModelProperty(value = "支付时间")
    private LocalDateTime payTime;

    // 手机号码
    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String tenantId;

    // 租户渠道ID
    @NotBlank(message = "租户渠道ID不能为空")
    @ApiModelProperty(value = "租户渠道ID")
    private String platformChannelId;


}