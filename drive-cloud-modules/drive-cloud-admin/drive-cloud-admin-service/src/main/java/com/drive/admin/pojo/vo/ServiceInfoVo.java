package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 客服人员信息表
 *
 * @author xiaoguo
 */
@Data
public class ServiceInfoVo {


	// 主键
	@Excel(name = "主键", width = 20)
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

	// 驾校ID（如果是驾校用户必须）
	@Excel(name = "驾校ID（如果是驾校用户必须）", width = 20)
	private String driveSchoolId;

	// 手机号
	@Excel(name = "手机号", width = 20)
	private String phone;

	// 密码
	@Excel(name = "密码", width = 20)
	private String password;

	// 性别
	@Excel(name = "性别", width = 20)
	private String sex;

	// 真实姓名
	@Excel(name = "真实姓名", width = 20)
	private String realName;

	// 身份证号
	@Excel(name = "身份证号", width = 20)
	private String idCard;

	// 使用状态(1：正常；2：停用)
	@Excel(name = "使用状态(1：正常；2：停用)", width = 20)
	private String status;

	// 头像路径
	@Excel(name = "头像路径", width = 20)
	private String headUrl;

	// 推荐人id
	@Excel(name = "推荐人id", width = 20)
	private String recommendUserId;

	// 自身推荐码
	@Excel(name = "自身推荐码", width = 20)
	private String recommendCodeSelf;

	// 评价分数
	@Excel(name = "评价分数", width = 20)
	private BigDecimal appraiseGrade;

	// token_key
	@Excel(name = "token_key", width = 20)
	private String tokeKey;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）
	@Excel(name = "客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）", width = 20)
	private String serviceType;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}