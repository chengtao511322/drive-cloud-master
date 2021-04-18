package com.drive.pay.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 支付交易流水信息表
 *
 * @author xiaoguo
 */
@Data
public class MentTransactionVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 支付金额
	@Excel(name = "支付金额", width = 20)
	private BigDecimal payAmount;

	// 支付状态;0待支付1已经支付2支付超时3支付失败
	@Excel(name = "支付状态;0待支付1已经支付2支付超时3支付失败", width = 20)
	private Long paymentStatus;

	@Excel(name = "", width = 20)
	private String userId;

	// 订单号
	@Excel(name = "订单号", width = 20)
	private String orderNo;

	// 乐观锁
	@Excel(name = "乐观锁", width = 20)
	private Integer revision;

	// 第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用
	@Excel(name = "第三方支付id 支付宝、银联等 在第三方支付渠道完成后分配一个支付id 对账使用", width = 20)
	private String partyPayId;

	// 使用雪花算法生产 支付系统 支付id
	@Excel(name = "使用雪花算法生产 支付系统 支付id", width = 20)
	private String paymentId;

	// 创建人
	@Excel(name = "创建人", width = 20)
	private String createUser;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 修改者
	@Excel(name = "修改者", width = 20)
	private String updateUser;

	// 支付渠道
	@Excel(name = "支付渠道", width = 20)
	private String paymentChannel;

	// 订单金额
	@Excel(name = "订单金额", width = 20)
	private BigDecimal orderAmount;

	// body
	@Excel(name = "body", width = 20)
	private String body;

	@Excel(name = "", width = 20)
	private String subject;

	@Excel(name = "", width = 20)
	private String payParams;

	// 支付时间
	@Excel(name = "支付时间", width = 20)
	private LocalDateTime payTime;

	// 手机号码
	@Excel(name = "手机号码", width = 20)
	private String phone;

	@Excel(name = "", width = 20)
	private String tenantId;

	// 租户渠道ID
	@Excel(name = "租户渠道ID", width = 20)
	private String platformChannelId;

}