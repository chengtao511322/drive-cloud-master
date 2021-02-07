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
 * 学员订单表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_student_order")
public class StudentOrderEntity extends BaseEntity {


	// 订单号
	@TableId(type= IdType.ID_WORKER)
	@TableField(value="order_no")
	private String orderNo;

	// 订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	private String orderType;

	// 下单客户(学员id)
	private String studentId;

	// 客服ID
	private String userId;

	// 支付类型(1-支付宝，2-微信)
	private String payType;

	// 考试报名单号
	private String testEnrollNo;

	// 学车报名单号
	private String studyEnrollNo;

	// 预约单号
	private String trainApplyNo;

	// 订单金额
	private BigDecimal orderAmount;

	// 应付金额
	private BigDecimal payableAmount;

	// 优惠券ID
	private String columcouponId;

	// 优惠券金额
	private BigDecimal couponAmount;

	// 订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）
	private String status;

	// 备注
	private String remark;

	// 支付时间（支付成功时间）
	private LocalDateTime payTime;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 是否删除：0否；1：是
	@TableLogic
	@TableField(value="is_delete")
	private String isDelete;

	// 微信预支付订单信息
	@TableField("wx_signInfo" )
	private String wxSigninfo;

	// 支付宝signInfo信息
	@TableField("ali_signInfo" )
	private String aliSigninfo;

	// 支付宝退款请求号（用于退款查询）
	private String aliOutRefundNo;

	// 微信退款请求号（用于退款查询）
	private String wxOutRefundNo;

	// 微信signInfo上一次创建时间（用于判断过期时间）
	@TableField("create_wx_signInfo_time" )
	private LocalDateTime createWxSigninfoTime;

	// 支付宝signInfo上一次创建时间（用于判断过期时间）
	@TableField("create_ali_signInfo_time" )
	private LocalDateTime createAliSigninfoTime;

	// 打折金额
	private BigDecimal discountAmount;

	// 打折产品id
	private String discountProductId;

	// 优惠卷领取表id
	private String couponUseId;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 对应的产品ID
	private String productId;

	// 新订单号
	private String newOrderNo;

	// 订单类型明细id
	private String classTypeId;

}