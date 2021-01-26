package com.drive.member.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;


/**
 * 学员信息表
 *
 * @author xiaoguo
 */
@Data
public class StudentInfoVo {


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
	private LocalDateTime recommendDate;

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
	private LocalDateTime loginTime;

	// 创建时间
	private LocalDateTime createTime;

	// 更新时间
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

}