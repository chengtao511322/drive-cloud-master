package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 平台为合作驾校开通账号后，将其驾校信息统一维护到该表中。
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class DriveSchoolPageQueryParam extends BasePageQueryParam {


	private String id;

	// 省
	private String provinceId;

	// 市
	private String cityId;

	// 区
	private String areaId;

	// 经度
	private String longitude;

	// 纬度
	private String latitude;

	// 详细地址
	private String address;

	// 联系人
	private String contacts;

	// 联系电话
	private String contactPhone;

	// 报名单价
	private BigDecimal enrollPrice;

	// 驾校名称
	private String schoolName;

	// 成立时间
	private LocalDateTime establishTime;

	// 资质照片地址List
	private String seniorityPath;

	// 驾校规模
	private String driveSchoolScale;

	// 驾校简介
	private String schoolDescription;

	// 是否可报名
	private String isEnroll;

	// 驾校后台管理用户id
	private String adminUserId;

	// 状态（1-正常，2-停用）
	private String status;

	// 是否独立结算(0-否，1-是)
	private String isAloneSettlement;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 报名单价(vip报名费)
	private BigDecimal vipEnrollPrice;

	// 模糊查询
	private String vagueSchoolName;

}