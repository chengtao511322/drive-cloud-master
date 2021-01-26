package com.drive.marketing.pojo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("mark_coupon")
public class CouponEntity extends BaseEntity {


	// id
	private String id;

	// 优惠卷类型；0->全场赠券；1->会员赠券；2->报名赠券；3->注册赠券;3->练车赠券
	private String type;

	// 优惠券名称
	private String name;

	// 使用平台：0->全部；1->移动；2->PC  ；3->微信 
	private String platform;

	// 总发行数量
	private Integer publishCount;

	// 金额
	private BigDecimal amount;

	// 每人限领张数
	private Integer perLimit;

	// 使用门槛；0表示无门槛  具体金额 满100 使用
	private BigDecimal minPoint;

	// 有效期 开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
	private LocalDateTime startTime;

	// 有效期 结束时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
	private LocalDateTime endTime;

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
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime releaseTime;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 状态 1  启用  2 停用
	private String status;

	// 删除状态 1  未删除  2 已经删除
	@TableLogic
	@TableField(value="is_delete")
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

	private String getType;

}