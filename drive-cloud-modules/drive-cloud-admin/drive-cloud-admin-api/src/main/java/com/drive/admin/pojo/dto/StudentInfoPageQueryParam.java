package com.drive.admin.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 学员信息表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class StudentInfoPageQueryParam extends BasePageQueryParam {


	private String id;

	// 省
	private String provinceId;

	// 市
	private String cityId;

	// 区
	private String areaId;

	// 手机
	private String phone;

	// 昵称
	private String username;

	// 密码
	private String password;

	// 身份证号
	private String idCard;

	// 真实姓名
	private String realName;

	// 年龄
	private Integer age;

	// 性别（男；女）
	private String sex;

	// 邮箱
	private String email;

	// 自身推荐码
	private String recommendCodeSelf;

	// 推荐人id
	private String recommendUserId;

	// 推荐类型（1-学员；2-教练；3-运维）
	private String recommendUserType;

	// 推荐新用户类型（1-收入现金，2-收入学车币）
	private String recommendIncomeType;

	// 推荐时间
	private String recommendDate;

	// 头像路径
	private String headUrl;

	// 微信openid
	private String wechatOpenid;

	// QQopenid
	private String qqOpenid;

	// token_key
	private String tokenKey;

	// 使用状态1：正常；2：停用
	private String status;

	// 登录时间
	@DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
	private LocalDateTime loginTime;

	// 创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
	private LocalDateTime createTime;

	// 更新时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
	private LocalDateTime updateTime;

	// 科目一考试是否通过（是，否）
	private String subject1TestResultType;

	// 科目二考试是否通过（是，否）
	private String subject2TestResultType;

	// 科目三考试是否通过（是，否）
	private String subject3TestResultType;

	// 科目四考试是否通过（是，否）
	private String subject4TestResultType;

	// 渠道  1：扫码 2：线上推广
	private Integer channel;

	// 设备唯一id
	private String phoneId;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 登录设备类型
	private String platform;

	// 注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）
	private String logonChannel;

	// 模糊查询对象
	private String vaguePhoneSearch;
	private String vagueUserNameSearch ;
	private String vagueRealNameSearch;
	private String vagueEmailSearch;

	private String vagueServiceNameSearch;
	private String vagueServicePhoneSearch;

	// 登录时间
	private String searchLoginDate;
	// 推荐时间
	private String searchRecommendDate;
	// 城市数组
	private String cityArr;

	private String createTimeSearch;


	// 是否
	private boolean returnVisitHistory;



	// 售前客服
	private String serviceId;


	// 预计下次回访时间
	private String nextReturnVisitTimeSearch;
	private String vagueSearch;

	private Integer isIntentionSearch;
	// 线上
	private String onLineServiceId;
	// 线下
	private String offlineServiceId;

	// 创建者
	private String operationUser;
	// 操作时间
	private LocalDateTime operationTime;

	private String[] dateTimeSearchArr = new String[2];
	// 注册时间
	private String[] regDateTimeSearchArr = new String[2];
	// 分配时间
	private String[] allocationDateTimeSearchArr = new String[2];

	// 0 否 1 是 是否有客服
	private Integer hasPreOnlineServicer;
}