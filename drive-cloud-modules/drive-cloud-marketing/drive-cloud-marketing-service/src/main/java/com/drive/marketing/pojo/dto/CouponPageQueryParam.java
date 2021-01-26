package com.drive.marketing.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CouponPageQueryParam extends BasePageQueryParam {


	// id
	private String id;

	// 优惠卷类型；0->全场赠券；1->会员赠券；2->报名赠券；3->注册赠券;3->练车赠券
	private String type;

	// 优惠券名称
	private String name;

	// 使用平台：0->全部；1->移动；2->PC  ；3->微信 
	private Integer platform;

	// 总发行数量
	private Integer publishCount;

	// 金额
	private BigDecimal amount;

	// 每人限领张数
	private Integer perLimit;

	// 使用门槛；0表示无门槛  具体金额 满100 使用
	private BigDecimal minPoint;

/*	// 有效期 开始时间
	private LocalDateTime startTime;

	// 有效期 结束时间
	private LocalDateTime endTime;*/

	// 使用类型：0->全场通用；1->指定分类；2->指定产品
	private String useType;

	private String note;

	// 已使用数量
	private Integer useCount;

	// 领取数量
	private Integer receiveCount;

	// 可领取日期
	private LocalDate enableTime;

	// 优惠码
	private String code;

	// 可领取的会员类型：0->无限时 
	private String memberLevel;

	// 发布日期
	private LocalDateTime releaseTime;

	// 创建时间
	private LocalDateTime createTime;

	// 修改时间
	private LocalDateTime updateTime;

	// 状态 1  启用  2 停用
	private String status;

	// 删除状态 1  未删除  2 已经删除
	private String isDelete;

	// 点击地址
	private String clickUrl;

	// 优惠券可使用天数
	private Integer useDay;

	// 运营位置
	private String operatingPosition;

	// 运营商
	private String operator;

	// 租户ID
	private String tenantId;

	private Integer getType;

}