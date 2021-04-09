package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 学员学车报名单
 *
 * @author xiaoguo
 */
@Data
public class StudentStudyEnrollVo {


	// 学员报名单号
	@Excel(name = "学员报名单号", width = 20)
	private String studyEnrollNo;

	// 订单号
	private String studentOrderNo;

	// 省-学员的省
	@Excel(name = "省-学员的省", width = 20)
	private String provinceId;

	// 市-学员的市
	@Excel(name = "市-学员的市", width = 20)
	private String cityId;

	// 区-学员的区
	@Excel(name = "区-学员的区", width = 20)
	private String areaId;

	// 经度
	@Excel(name = "经度", width = 20)
	private String longitude;

	// 纬度
	@Excel(name = "纬度", width = 20)
	private String latitude;

	// 学员ID
	@Excel(name = "学员ID", width = 20)
	private String studentId;

	// 真实姓名
	@Excel(name = "真实姓名", width = 20)
	private String realName;

	// 身份证号
	@Excel(name = "身份证号", width = 20)
	private String idCard;

	// 电话号码
	@Excel(name = "电话号码", width = 20)
	private String telephone;

	// 年龄
	@Excel(name = "年龄", width = 20)
	private Integer age;

	// 性别（男；女）
	@Excel(name = "性别（男；女）", width = 20)
	private String sex;

	// 驾照类型（c1；c2...）
	@Excel(name = "驾照类型（c1；c2...）", width = 20)
	private String driveType;

	// 平台服务费用
	@Excel(name = "平台服务费用", width = 20)
	private BigDecimal serviceCharge;

	// 报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）
	@Excel(name = "报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）", width = 20)
	private String enrollStatus;

	// 报名驾校id
	@Excel(name = "报名驾校id", width = 20)
	private String driveSchoolId;


	// 报名驾校名称（非平台报名人员补充资料）
	@Excel(name = "报名驾校名称（非平台报名人员补充资料）", width = 20)
	private String driveSchoolName;

	// 报名单价(平台总的报名价格)
	@Excel(name = "报名单价(平台总的报名价格)", width = 20)
	private BigDecimal price;

	// 身份证图片正面
	@Excel(name = "身份证图片正面", width = 20)
	private String idCardPathPositive;

	// 身份证图片反面
	@Excel(name = "身份证图片反面", width = 20)
	private String idCardPathNegative;

	// 居住证图片正面
	@Excel(name = "居住证图片正面", width = 20)
	private String liveProvePositive;

	// 居住证图片反面
	@Excel(name = "居住证图片反面", width = 20)
	private String liveProveNegative;

	// 联系地址
	@Excel(name = "联系地址", width = 20)
	private String address;

	// 体检结果照片
	@Excel(name = "体检结果照片", width = 20)
	private String testResultPath;

	// 交管网上报名账号
	@Excel(name = "交管网上报名账号", width = 20)
	private String enrollAccountNo;

	// 交管网上报名密码
	@Excel(name = "交管网上报名密码", width = 20)
	private String enrollPassword;

	// 驾校实际报名时间
	@Excel(name = "驾校实际报名时间", width = 20)
	private LocalDateTime actualEnrollTime;

	// 客服ID（系统用户ID）
	@Excel(name = "客服ID（系统用户ID）", width = 20)
	private String userId;
	// 线上客服名称
	private String onlineServiceName;
	// 线下
	private String lineServiceName;

	// 线下客服id(驾校客服，报名接送)
	@Excel(name = "线下客服id(驾校客服，报名接送)", width = 20)
	private String lineUnderUserId;

	// 预约见面时间
	@Excel(name = "预约见面时间", width = 20)
	private LocalDateTime beSpeakMeetTime;

	// 预约见面地址
	@Excel(name = "预约见面地址", width = 20)
	private String beSpeakMeetAddress;

	// 取消时间
	@Excel(name = "取消时间", width = 20)
	private LocalDateTime cancelTime;

	// 取消原因
	@Excel(name = "取消原因", width = 20)
	private String cancelReason;

	// 是否有意向报名（是，否）
	@Excel(name = "是否有意向报名（是，否）", width = 20)
	private String isIntentEnroll;

	// 联系时间
	@Excel(name = "联系时间", width = 20)
	private LocalDateTime contactTime;

	// 意向报名时间
	@Excel(name = "意向报名时间", width = 20)
	private LocalDateTime intentEnrollTime;

	// 意向报名驾校id
	@Excel(name = "意向报名驾校id", width = 20)
	private String intentEnrollSchoolId;

	// 线上报名时间
	@Excel(name = "线上报名时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 是否选择接送
	@Excel(name = "是否选择接送", width = 20)
	private String isMeetGive;

	// 接送费用
	@Excel(name = "接送费用", width = 20)
	private BigDecimal meetGicePrice;

	// 是否为学生
	@Excel(name = "是否为学生", width = 20)
	private String isStudent;

	// 学生所属学校id
	@Excel(name = "学生所属学校id", width = 20)
	private String studentSchoolId;

	// 身份证是否归还(是，否)
	@Excel(name = "身份证是否归还(是，否)", width = 20)
	private String idIsReturn;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 报名类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班
	@Excel(name = "报名类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班", width = 20)
	private Integer enrollType;

	// 是否还在平台学习（0-否，1-是）
	@Excel(name = "是否还在平台学习（0-否，1-是）", width = 20)
	private String isInStudy;


	private String provinceName;
	private String cityName;
	private String areaName;


	// 回访时间
	private LocalDateTime returnVisitTime;
	// 回访客服
	private String returnVisitServiceName;
	// 回访内容
	private String returnVisitContent;


	private StudentOrderVo studentOrderVo;
	private StudentInfoVo studentVo;


	// 是否
	private boolean returnVisitHistory;

	// 转换类型(1-平台转化，2-新用户转化，3-待支付转化)
	private String conversionType;
	// 支付时间
	private String payTime;
	// 订单状态
	private String orderStatus;

	private int cancelNum;

	private LocalDateTime orderTime;

	private ServiceReturnVisitHistoryVo serviceReturnVisitHistory;


	// 操作类型 1：（后台）客服取消 2：学员取消
	private String operationType;
}