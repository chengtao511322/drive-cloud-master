package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.admin.util.AdminCacheUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 学员订单表
 *
 * @author xiaoguo
 */
@Data
public class StudentOrderVo implements java.io.Serializable {




	// 订单号
	@Excel(name = "订单号", width = 20)
	private String orderNo;

	// 订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	@Excel(name = "订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）", width = 20)
	private String orderType;

	// 下单客户(学员id)
	@Excel(name = "下单客户(学员id)", width = 20)
	private String studentId;

	// 客服ID
	@Excel(name = "客服ID", width = 20)
	private String userId;

	// 支付类型(1-支付宝，2-微信)
	@Excel(name = "支付类型(1-支付宝，2-微信)", width = 20)
	private String payType;

	// 考试报名单号
	@Excel(name = "考试报名单号", width = 20)
	private String testEnrollNo;

	// 学车报名单号
	@Excel(name = "学车报名单号", width = 20)
	private String studyEnrollNo;

	// 预约单号
	@Excel(name = "预约单号", width = 20)
	private String trainApplyNo;

	// 订单金额
	@Excel(name = "订单金额", width = 20)
	private BigDecimal orderAmount;

	// 应付金额
	@Excel(name = "应付金额", width = 20)
	private BigDecimal payableAmount;

	// 优惠券ID
	@Excel(name = "优惠券ID", width = 20)
	private String columcouponId;

	// 优惠券金额
	@Excel(name = "优惠券金额", width = 20)
	private BigDecimal couponAmount;

	// 订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）
	@Excel(name = "订单状态: （1-待支付,2-支付成功,3-支付处理中，4-支付失败，5-已取消(（未支付时取消，已支付在取消的是-退款成功)，6-待评价，7-已评价, 8-退款成功,9-退款处理中）", width = 20)
	private String status;

	// 备注
	@Excel(name = "备注", width = 20)
	private String remark;

	// 支付时间（支付成功时间）
	@Excel(name = "支付时间（支付成功时间）", width = 20)
	private LocalDateTime payTime;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 是否删除：0否；1：是
	@Excel(name = "是否删除：0否；1：是", width = 20)
	private String isDelete;

	// 微信预支付订单信息
	@Excel(name = "微信预支付订单信息", width = 20)
	private String wxSigninfo;

	// 支付宝signInfo信息
	@Excel(name = "支付宝signInfo信息", width = 20)
	private String aliSigninfo;

	// 支付宝退款请求号（用于退款查询）
	@Excel(name = "支付宝退款请求号（用于退款查询）", width = 20)
	private String aliOutRefundNo;

	// 微信退款请求号（用于退款查询）
	@Excel(name = "微信退款请求号（用于退款查询）", width = 20)
	private String wxOutRefundNo;

	// 微信signInfo上一次创建时间（用于判断过期时间）
	@Excel(name = "微信signInfo上一次创建时间（用于判断过期时间）", width = 20)
	private LocalDateTime createWxSigninfoTime;

	// 支付宝signInfo上一次创建时间（用于判断过期时间）
	@Excel(name = "支付宝signInfo上一次创建时间（用于判断过期时间）", width = 20)
	private LocalDateTime createAliSigninfoTime;

	// 打折金额
	@Excel(name = "打折金额", width = 20)
	private BigDecimal discountAmount;

	// 打折产品id
	@Excel(name = "打折产品id", width = 20)
	private String discountProductId;

	// 优惠卷领取表id
	@Excel(name = "优惠卷领取表id", width = 20)
	private String couponUseId;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 对应的产品ID
	@Excel(name = "对应的产品ID", width = 20)
	private String productId;

	private String className;

	// 新订单号
	@Excel(name = "新订单号", width = 20)
	private String newOrderNo;

	// 订单类型明细id
	@Excel(name = "订单类型明细id", width = 20)
	private String classTypeId;

	// 学员姓名
	private String studentName;

	private String onlineServiceName;
	private String lineServiceName;

	private StudentInfoVo studentVo;
	private String provinceName;
	private String cityName;
	private String areaName;
	// 总课时
	private int totalHour;

	private String coachName;

	// 商户号
	private String mchId;

	private StudentStudyEnrollVo studentStudyEnrollVo;
	private StudentTrainCarApplyVo studentTrainCarApplyVo;

	public void setProductId(String productId) {
		this.productId = productId;
		this.className = AdminCacheUtil.getClassName(productId);
	}
}