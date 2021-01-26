package com.drive.marketing.pojo.vo;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 岗位信息
 *
 * @author DreamChan
 */
@Data
public class ActivityInfoVo {


	private String id;

	private String zoneName;

	/**
	 * 专区封面图路径
	 */
	private String zoneImgPath;

	/**
	 * 标题
	 */
	private String zoneTitle;

	/**
	 * 活动开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	private LocalDateTime startTime;

	/**
	 * 活动结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	private LocalDateTime endTime;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	private LocalDateTime updateTime;

	/**
	 * 状态启用，0：否，1是
	 */
	private String status;

	/**
	 * 删除状态：0 没有删除，1：已经删除
	 */
	private String isDelete;

	/**
	 * 添加者
	 */
	private String addUser;

	/**
	 * 模板
	 */
	private String template;

	/**
	 * 热门级别
	 */
	private Integer hot;

	/**
	 * 优惠卷类型；0->全场活动；活动类型  1 学车报名  2考试报名 3常规训练 4 考试训练  5大礼包活动
	 */
	private String type;

	private String ruleId;

	/**
	 * 活动价格
	 */
	private BigDecimal activityPrice;

	/**
	 * 原价
	 */
	private BigDecimal originalCost;

	private String strategyBeanId;

	/**
	 * 运营位置 ，存放省市区编码，多个用逗号隔开
	 */
	private String operatingPosition;


	/**
	 * 是否首选  0  否 1  是
	 */
	private String isFirst;

	/**
	 * 租户ID
	 */
	private String tenantId;

	private String tenantName;

	/**
	 * 专区描述
	 */
	private String description;

	/**
	 * 静态化地址
	 */
	private String staticLink;

	//推广说明
	private String promotionExplain;

	// 推广地址
	private String promotionUrl;

}