package com.drive.apply.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 学员学车报名单
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class StudentStudyEnrollPageQueryParam extends BasePageQueryParam {


	// 学员报名单号
	private String studyEnrollNo;
	// 练车状态
	private String drivingStatus;
	// 学员订单号
	private String studentOrderNo;

	// 省-学员的省
	private String provinceId;

	// 市-学员的市
	private String cityId;

	// 区-学员的区
	private String areaId;

	// 经度
	private String longitude;

	// 纬度
	private String latitude;

	// 学员ID
	private String studentId;

	private String classType;

	private String recommendUserId;

	// 真实姓名
	private String realName;

	// 身份证号
	private String idCard;

	// 电话号码
	private String telephone;

	// 年龄
	private Integer age;

	// 性别（男；女）
	private String sex;

	// 驾照类型（c1；c2...）
	private String driveType;

	// 平台服务费用
	private BigDecimal serviceCharge;

	// 报名状态（1-提交报名；2-已联系待支付；3-已支付待备案；5-支付失败 ;6-报名完成 ;7-报名取消;8-报名失败 ;9-已退款，10-自动报名完成，11-自动报名待审核，12-已备案待审核,13-密码已提交待审核）
	private String enrollStatus;
	private String enrollStatusArr;

	// 报名驾校id
	private String driveSchoolId;

	// 报名驾校名称（非平台报名人员补充资料）
	private String driveSchoolName;

	// 报名单价(平台总的报名价格)
	private BigDecimal price;

	// 身份证图片正面
	private String idCardPathPositive;

	// 身份证图片反面
	private String idCardPathNegative;

	// 居住证图片正面
	private String liveProvePositive;

	// 居住证图片反面
	private String liveProveNegative;

	// 联系地址
	private String address;

	// 体检结果照片
	private String testResultPath;

	// 交管网上报名账号
	private String enrollAccountNo;

	// 交管网上报名密码
	private String enrollPassword;

	// 驾校实际报名时间
	private LocalDateTime actualEnrollTime;

	// 客服ID（系统用户ID）
	private String userId;

	// 线下客服id(驾校客服，报名接送)
	private String lineUnderUserId;

	// 预约见面时间
	private LocalDateTime beSpeakMeetTime;
	// 查询
	private String beSpeakMeetTimeSearch;

	// 预约见面地址
	private String beSpeakMeetAddress;

	// 取消时间
	private LocalDateTime cancelTime;

	// 取消原因
	private String cancelReason;

	// 是否有意向报名（是，否）
	private String isIntentEnroll;

	// 联系时间
	private LocalDateTime contactTime;

	// 意向报名时间
	private LocalDateTime intentEnrollTime;

	private String intentEnrollTimeSearch;

	// 意向报名驾校id
	private String intentEnrollSchoolId;

	// 线上报名时间
	private LocalDateTime createTime;

	// 更新时间
	private LocalDateTime updateTime;

	// 是否选择接送
	private String isMeetGive;

	// 接送费用
	private BigDecimal meetGicePrice;

	// 是否为学生
	private String isStudent;

	// 学生所属学校id
	private String studentSchoolId;

	// 身份证是否归还(是，否)
	private String idIsReturn;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 报名类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班
	private Integer enrollType;

	// 是否还在平台学习（0-否，1-是）
	private String isInStudy;

	// 模糊查询
	private String vagueStudyEnrollNoSearch;
	// 下次回访时间查询
	private String nextReturnVisitTimeSearch;
	// 创建订单时间
	private String createTimeSearch;


	// 是否
	private boolean returnVisitHistory;
	private Integer isReturnVisit;

	// 转换类型(1-平台转化，2-新用户转化，3-待支付转化)
	private String conversionType;
	// 订单状态
	private String orderStatusSearch;
	// 状态ARR
	private String[] orderStatusArr;
	//支付时间
	private String payTimeSearch;

	// 操作类型 1：（后台）客服取消 2：学员取消
	private String operationType;
	// 科目类型
	private String subjectType;
	// 约考状态
	private String aboutStatus;

}