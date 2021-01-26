package com.drive.marketing.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 活动优惠券关联表
 *
 * @author xiaoguo
 */
@Data
public class ActivityCouponRelationVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 优惠券ID
	@Excel(name = "优惠券ID", width = 20)
	private String couponId;

	// 活动id
	@Excel(name = "活动id", width = 20)
	private String activityId;

	@Excel(name = "发行数量", width = 20)
	private Integer publishCount;

	@Excel(name = "使用数量", width = 20)
	private Integer useCount;

	@Excel(name = "使用数量", width = 20)
	private BigDecimal amount;

	@Excel(name = "优惠券数量", width = 20)
	private String couponNum;


	@Excel(name = "优惠券使用数量", width = 20)
	private String receiveCount;

	@Excel(name = "优惠券名称", width = 20)
	private String name;

	// 优惠券名称
	private String couponName;

}