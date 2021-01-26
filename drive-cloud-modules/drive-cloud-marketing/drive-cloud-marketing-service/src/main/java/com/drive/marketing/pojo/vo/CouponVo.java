package com.drive.marketing.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 
 *
 * @author xiaoguo
 */
@Data
public class CouponVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 优惠卷类型；0->全场赠券；1->会员赠券；2->报名赠券；3->注册赠券;3->练车赠券
	@Excel(name = "优惠卷类型；0->全场赠券；1->会员赠券；2->报名赠券；3->注册赠券;3->练车赠券", width = 20)
	private String type;

	// 优惠券名称
	@Excel(name = "优惠券名称", width = 20)
	private String name;

	// 使用平台：0->全部；1->移动；2->PC  ；3->微信 
	@Excel(name = "使用平台：0->全部；1->移动；2->PC  ；3->微信 ", width = 20)
	private Integer platform;

	// 总发行数量
	@Excel(name = "总发行数量", width = 20)
	private Integer publishCount;

	// 金额
	@Excel(name = "金额", width = 20)
	private BigDecimal amount;

	// 每人限领张数
	@Excel(name = "每人限领张数", width = 20)
	private Integer perLimit;

	// 使用门槛；0表示无门槛  具体金额 满100 使用
	@Excel(name = "使用门槛；0表示无门槛  具体金额 满100 使用", width = 20)
	private BigDecimal minPoint;

	// 有效期 开始时间
	@Excel(name = "有效期 开始时间", width = 20)
	private LocalDateTime startTime;

	// 有效期 结束时间
	@Excel(name = "有效期 结束时间", width = 20)
	private LocalDateTime endTime;

	// 使用类型：0->全场通用；1->指定分类；2->指定产品
	@Excel(name = "使用类型：0->全场通用；1->指定分类；2->指定产品", width = 20)
	private String useType;

	@Excel(name = "", width = 20)
	private String note;

	// 已使用数量
	@Excel(name = "已使用数量", width = 20)
	private Integer useCount;

	// 领取数量
	@Excel(name = "领取数量", width = 20)
	private Integer receiveCount;

	// 可领取日期
	@Excel(name = "可领取日期", width = 20)
	private LocalDate enableTime;

	// 优惠码
	@Excel(name = "优惠码", width = 20)
	private String code;

	// 可领取的会员类型：0->无限时 
	@Excel(name = "可领取的会员类型：0->无限时 ", width = 20)
	private String memberLevel;

	// 发布日期
	@Excel(name = "发布日期", width = 20)
	private LocalDateTime releaseTime;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 状态 1  启用  2 停用
	@Excel(name = "状态 1  启用  2 停用", width = 20)
	private String status;

	// 删除状态 1  未删除  2 已经删除
	@Excel(name = "删除状态 1  未删除  2 已经删除", width = 20)
	private String isDelete;

	// 点击地址
	@Excel(name = "点击地址", width = 20)
	private String clickUrl;

	// 优惠券可使用天数
	@Excel(name = "优惠券可使用天数", width = 20)
	private Integer useDay;

	// 运营位置
	@Excel(name = "运营位置", width = 20)
	private String operatingPosition;

	// 运营商
	@Excel(name = "运营商", width = 20)
	private String operator;

	// 租户ID
	@Excel(name = "租户ID", width = 20)
	private String tenantId;

	@Excel(name = "", width = 20)
	private Integer getType;

	// 类型名称
	private String typeName;

	private CouponProductRelationVo couponProductRelation;


	public void setType(String type) {
		this.type = type;
		// 0->全场赠券；1->会员赠券；2->报名赠券；3->注册赠券;3->练车赠券
		if (type.equals("0")) {//语句
			this.typeName = "全场赠券";
			//可选
		} else if (type .equals("1")) {//语句
			this.typeName = "会员赠券";
			//可选
			//你可以有任意数量的case语句
		} else if (type .equals("2")) {//语句
			this.typeName = "报名赠券";
			//可选
			//你可以有任意数量的case语句
		} else if (type .equals("3")) {//语句
			this.typeName = "注册赠券";
			//可选
		} else if (type .equals("4")) {//语句
			this.typeName = "练车赠券";
			//可选
			//你可以有任意数量的case语句
		}
	}
}