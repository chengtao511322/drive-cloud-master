package com.drive.admin.pojo.dto;

import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 客服人员信息表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ServiceInfoPageQueryParam extends BasePageQueryParam {


	// 主键
	private String id;

	// 省
	private String provinceId;

	// 市
	private String cityId;

	// 区
	private String areaId;

	// 驾校ID（如果是驾校用户必须）
	private String driveSchoolId;

	// 手机号
	private String phone;

	// 密码
	private String password;

	// 性别
	private String sex;

	// 真实姓名
	private String realName;

	// 身份证号
	private String idCard;

	// 使用状态(1：正常；2：停用)
	private String status;

	// 头像路径
	private String headUrl;

	// 推荐人id
	private String recommendUserId;

	// 自身推荐码
	private String recommendCodeSelf;

	// 评价分数
	private BigDecimal appraiseGrade;

	// token_key
	private String tokeKey;

	// 创建时间
	private LocalDateTime createTime;

	// 更新时间
	private LocalDateTime updateTime;

	// 客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）
	private String serviceType;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 登录账号
	private String loginAccount;

	// 模糊查询真实姓名
	private String vagueRealNameSearch;
	// 模糊查询手机号
	private String vaguePhoneSearch;
	// 模糊查询登录账户
	private String vagueLoginAccountSearch;

}