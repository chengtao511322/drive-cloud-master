package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.admin.util.AdminCacheUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 教练信息表
 *
 * @author xiaoguo
 */
@Data
public class CoachInfoVo {


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

	// 电话
	@Excel(name = "电话", width = 20)
	private String phone;

	// 登录密码
	@Excel(name = "登录密码", width = 20)
	private String password;

	// 真实姓名
	@Excel(name = "真实姓名", width = 20)
	private String realName;

	// 身份证号码
	@Excel(name = "身份证号码", width = 20)
	private String idCard;

	// 身份证图片正面
	@Excel(name = "身份证图片正面", width = 20)
	private String idCardPathPositive;

	// 身份证图片反面
	@Excel(name = "身份证图片反面", width = 20)
	private String idCardPathNegative;

	// 邮箱
	@Excel(name = "邮箱", width = 20)
	private String email;

	// 年龄
	@Excel(name = "年龄", width = 20)
	private Integer age;

	// 教龄
	@Excel(name = "教龄", width = 20)
	private Integer teachYears;

	// 性别
	@Excel(name = "性别", width = 20)
	private String sex;

	// 教练证照片
	@Excel(name = "教练证照片", width = 20)
	private String teachLicensePath;

	// 教练证号码
	@Excel(name = "教练证号码", width = 20)
	private String teachLicenseNo;

	// 是否为金牌教练
	@Excel(name = "是否为金牌教练", width = 20)
	private String isVip;

	// 状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交5-保险过期预警期
	@Excel(name = "状态，0：待审，1：驳回，2：正常：3禁用, 4-未提交 5-保险过期预警期", width = 20)
	private String status;

	// 自身推荐码
	@Excel(name = "自身推荐码", width = 20)
	private String recommendCodeSelf;

	// 推荐人id
	@Excel(name = "推荐人id", width = 20)
	private String recommendUserId;

	// 推荐类型（1-学员；2-教练；3-运维）
	@Excel(name = "推荐类型（1-学员；2-教练；3-运维）", width = 20)
	private String recommendUserType;

	// 推荐时间
	@Excel(name = "推荐时间", width = 20)
	private LocalDateTime recommendDate;

	// 评价分数
	@Excel(name = "评价分数", width = 20)
	private BigDecimal appraiseGrade;

	// token_key
	@Excel(name = "token_key", width = 20)
	private String tokeKey;

	// 45度车辆照片
	@Excel(name = "45度车辆照片", width = 20)
	private String carPhotoPath;

	// 品牌型号
	@Excel(name = "品牌型号", width = 20)
	private String brandModel;

	// 颜色
	@Excel(name = "颜色", width = 20)
	private String color;

	// 车牌号
	@Excel(name = "车牌号", width = 20)
	private String vehicleNo;

	// 工作证照片路径
	@Excel(name = "工作证照片路径", width = 20)
	private String workPath;

	// 行驶证照片
	@Excel(name = "行驶证照片", width = 20)
	private String drivingLicensePath;

	// 车架号
	@Excel(name = "车架号", width = 20)
	private String carFrameNo;

	// 发动机排量
	@Excel(name = "发动机排量", width = 20)
	private String engineDisplace;

	// 发动机号码
	@Excel(name = "发动机号码", width = 20)
	private String engineId;

	// 纬度
	@Excel(name = "纬度", width = 20)
	private String latitude;

	// 经度
	@Excel(name = "经度", width = 20)
	private String longitude;

	// 车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)
	@Excel(name = "车辆所有人 (与《机动车登记证书》所注明的车辆所有人一致)", width = 20)
	private String ownerName;

	// 车辆注册日期(以行驶证为准)
	@Excel(name = "车辆注册日期(以行驶证为准)", width = 20)
	private LocalDate certifyDate;

	// 教练证有效期起
	@Excel(name = "教练证有效期起", width = 20)
	private LocalDate licenseOn;

	// 教练证有效期止
	@Excel(name = "教练证有效期止", width = 20)
	private LocalDate licenseOff;

	// 审核结果说明
	@Excel(name = "审核结果说明", width = 20)
	private String examineResultExplain;

	// 注册时间
	@Excel(name = "注册时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 保险单照片
	@Excel(name = "保险单照片", width = 20)
	private String insurancePath;

	// 保险开始时间
	@Excel(name = "保险开始时间", width = 20)
	private LocalDate insuranceStartDate;

	// 保险截止时间
	@Excel(name = "保险截止时间", width = 20)
	private LocalDate insuranceEndDate;

	// 车型（1-公车，2-私车）
	@Excel(name = "车型（1-公车，2-私车）", width = 20)
	private String carType;

	// 公车所属驾校id
	@Excel(name = "公车所属驾校id", width = 20)
	private String carSchoolId;
	private String carSchoolName;

	// 教练提成百分比
	@Excel(name = "教练提成百分比", width = 20)
	private BigDecimal trainCoachChargePercent;

	// 公车提成百分比
	@Excel(name = "公车提成百分比", width = 20)
	private BigDecimal carChargePercent;

	// 平台提成百分比
	@Excel(name = "平台提成百分比", width = 20)
	private BigDecimal serviceChangePercent;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 自动发科 1 是 2否
	@Excel(name = "自动发科 1 是 2否", width = 20)
	private Integer autoSendClass;

	// 绩效归属 1  教练自己 2  驾校
	@Excel(name = "绩效归属 1  教练自己 2  驾校", width = 20)
	private Integer performanceAffiliation;

	@Excel(name = "", width = 20)
	private Integer topSort;

	private String provinceName;
	private String cityName;
	private String areaName;

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
		this.provinceName = AdminCacheUtil.getAreaName(provinceId);
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
		this.cityName = AdminCacheUtil.getAreaName(cityId);
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
		this.areaName = AdminCacheUtil.getAreaName(areaId);
	}
}