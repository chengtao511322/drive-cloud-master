package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
 *
 * @author xiaoguo
 */
@Data
public class DriveSchoolVo {


	@Excel(name = "", width = 20)
	private String id;

	// 省
	@Excel(name = "省", width = 20)
	private String provinceId;

	// 市
	@Excel(name = "市", width = 20)
	private String cityId;

	// 区
	@Excel(name = "区", width = 20)
	private String areaId;

	// 经度
	@Excel(name = "经度", width = 20)
	private String longitude;

	// 纬度
	@Excel(name = "纬度", width = 20)
	private String latitude;

	// 详细地址
	@Excel(name = "详细地址", width = 20)
	private String address;

	// 联系人
	@Excel(name = "联系人", width = 20)
	private String contacts;

	// 联系电话
	@Excel(name = "联系电话", width = 20)
	private String contactPhone;

	// 报名单价
	@Excel(name = "报名单价", width = 20)
	private BigDecimal enrollPrice;

	// 驾校名称
	@Excel(name = "驾校名称", width = 20)
	private String schoolName;

	// 成立时间
	@Excel(name = "成立时间", width = 20)
	private LocalDateTime establishTime;

	// 资质照片地址List
	@Excel(name = "资质照片地址List", width = 20)
	private String seniorityPath;

	// 驾校规模
	@Excel(name = "驾校规模", width = 20)
	private String driveSchoolScale;

	// 驾校简介
	@Excel(name = "驾校简介", width = 20)
	private String schoolDescription;

	// 是否可报名
	@Excel(name = "是否可报名", width = 20)
	private String isEnroll;

	// 驾校后台管理用户id
	@Excel(name = "驾校后台管理用户id", width = 20)
	private String adminUserId;

	// 状态（1-正常，2-停用）
	@Excel(name = "状态（1-正常，2-停用）", width = 20)
	private String status;

	// 是否独立结算(0-否，1-是)
	@Excel(name = "是否独立结算(0-否，1-是)", width = 20)
	private String isAloneSettlement;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 报名单价(vip报名费)
	@Excel(name = "报名单价(vip报名费)", width = 20)
	private BigDecimal vipEnrollPrice;


	private String provinceName;
	private String cityName;
	private String areaName;

}