package com.drive.pay.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付交易流水信息表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class MentTransactionPageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 支付金额
	@ApiModelProperty(value = "支付金额")
	private BigDecimal payAmount;
	// 模糊查询字段

	// 支付状态;0待支付1已经支付2支付超时3支付失败
	@ApiModelProperty(value = "支付状态;0待支付1已经支付2支付超时3支付失败")
	private Long paymentStatus;
	// 模糊查询字段

	@ApiModelProperty(value = "")
	private String userId;
	// 模糊查询字段

	// 订单号
	@ApiModelProperty(value = "订单号")
	private String orderNo;
	// 模糊查询字段

	// 乐观锁
	@ApiModelProperty(value = "乐观锁")
	private Integer revision;
	// 模糊查询字段

	// 第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用
	@ApiModelProperty(value = "第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用")
	private String partyPayId;
	// 模糊查询字段

	// 使用雪花算法生产 支付系统 支付id
	@ApiModelProperty(value = "使用雪花算法生产 支付系统 支付id")
	private String paymentId;
	// 模糊查询字段

	// 创建人
	@ApiModelProperty(value = "创建人")
	private String createUser;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 修改者
	@ApiModelProperty(value = "修改者")
	private String updateUser;
	// 模糊查询字段

	// 支付渠道
	@ApiModelProperty(value = "支付渠道")
	private String paymentChannel;
	// 模糊查询字段

	// 订单金额
	@ApiModelProperty(value = "订单金额")
	private BigDecimal orderAmount;
	// 模糊查询字段

	// body
	@ApiModelProperty(value = "body")
	private String body;
	// 模糊查询字段

	@ApiModelProperty(value = "")
	private String subject;
	// 模糊查询字段

	@ApiModelProperty(value = "")
	private String payParams;
	// 模糊查询字段

	// 支付时间
	@ApiModelProperty(value = "支付时间")
	private LocalDateTime payTime;
	// 模糊查询字段

	// 手机号码
	@ApiModelProperty(value = "手机号码")
	private String phone;
	// 模糊查询字段

	@ApiModelProperty(value = "")
	private String tenantId;
	// 模糊查询字段

	// 租户渠道ID
	@ApiModelProperty(value = "租户渠道ID")
	private String platformChannelId;
	// 模糊查询字段
	//private String vagueNameSearch

}