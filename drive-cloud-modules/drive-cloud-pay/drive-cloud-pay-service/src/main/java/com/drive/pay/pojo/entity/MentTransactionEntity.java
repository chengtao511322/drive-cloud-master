package com.drive.pay.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 支付交易流水信息表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("pay_ment_transaction")
public class MentTransactionEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 支付金额
	private BigDecimal payAmount;

	// 支付状态;0待支付1已经支付2支付超时3支付失败
	private Long paymentStatus;

	private String userId;

	// 订单号
	private String orderNo;

	// 乐观锁
	private Integer revision;

	// 第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用
	private String partyPayId;

	// 使用雪花算法生产 支付系统 支付id
	private String paymentId;

	// 创建人
	private String createUser;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 修改者
	private String updateUser;

	// 支付渠道
	private String paymentChannel;

	// 订单金额
	private BigDecimal orderAmount;

	// body
	private String body;

	private String subject;

	private String payParams;

	// 支付时间
	private LocalDateTime payTime;

	// 手机号码
	private String phone;

	private String tenantId;

	// 租户渠道ID
	private String platformChannelId;

}