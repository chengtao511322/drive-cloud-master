package com.drive.member.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 学员信息表
 *
 * @author xiaoguo
 */
@Data
public class StudentInfoVo {


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

	// 手机
	@Excel(name = "手机", width = 20)
	private String phone;

	// 昵称
	@Excel(name = "昵称", width = 20)
	private String username;

	// 密码
	@Excel(name = "密码", width = 20)
	private String password;

	// 身份证号
	@Excel(name = "身份证号", width = 20)
	private String idCard;

	// 真实姓名
	@Excel(name = "真实姓名", width = 20)
	private String realName;

	// 年龄
	@Excel(name = "年龄", width = 20)
	private Integer age;

	// 性别（男；女）
	@Excel(name = "性别（男；女）", width = 20)
	private String sex;

	// 邮箱
	@Excel(name = "邮箱", width = 20)
	private String email;

	// 自身推荐码
	@Excel(name = "自身推荐码", width = 20)
	private String recommendCodeSelf;

	// 推荐人id
	@Excel(name = "推荐人id", width = 20)
	private String recommendUserId;

	// 推荐类型（1-学员；2-教练；3-运维）
	@Excel(name = "推荐类型（1-学员；2-教练；3-运维）", width = 20)
	private String recommendUserType;

	// 推荐新用户类型（1-收入现金，2-收入学车币）
	@Excel(name = "推荐新用户类型（1-收入现金，2-收入学车币）", width = 20)
	private String recommendIncomeType;

	// 推荐时间
	@Excel(name = "推荐时间", width = 20)
	private LocalDateTime recommendDate;

	// 头像路径
	@Excel(name = "头像路径", width = 20)
	private String headUrl;

	// 微信openid
	@Excel(name = "微信openid", width = 20)
	private String wechatOpenid;

	// QQopenid
	@Excel(name = "QQopenid", width = 20)
	private String qqOpenid;

	// token_key
	@Excel(name = "token_key", width = 20)
	private String tokenKey;

	// 使用状态1：正常；2：停用
	@Excel(name = "使用状态1：正常；2：停用", width = 20)
	private String status;

	// 登录时间
	@Excel(name = "登录时间", width = 20)
	private LocalDateTime loginTime;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 科目一考试是否通过（是，否）
	@Excel(name = "科目一考试是否通过（是，否）", width = 20)
	private String subject1TestResultType;

	// 科目二考试是否通过（是，否）
	@Excel(name = "科目二考试是否通过（是，否）", width = 20)
	private String subject2TestResultType;

	// 科目三考试是否通过（是，否）
	@Excel(name = "科目三考试是否通过（是，否）", width = 20)
	private String subject3TestResultType;

	// 科目四考试是否通过（是，否）
	@Excel(name = "科目四考试是否通过（是，否）", width = 20)
	private String subject4TestResultType;

	// 渠道  1：扫码 2：线上推广
	@Excel(name = "渠道  1：扫码 2：线上推广", width = 20)
	private Integer channel;

	// 设备唯一id
	@Excel(name = "设备唯一id", width = 20)
	private String phoneId;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 登录设备类型
	@Excel(name = "登录设备类型", width = 20)
	private String platform;

	// 注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）
	@Excel(name = "注册渠道（直接存入中文，为防止活动平凡切换，造成字典表无法适应）", width = 20)
	private String logonChannel;

}