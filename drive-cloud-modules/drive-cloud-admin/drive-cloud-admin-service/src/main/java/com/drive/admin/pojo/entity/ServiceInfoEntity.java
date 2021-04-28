package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 客服人员信息表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_service_info")
public class ServiceInfoEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
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
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 客服类型（1-报名客服（线上客服）,2-线下客服(考试接送人员),3-超级客服(超级客服管理员), 4-考试客服(线上客服)）
	private String serviceType;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 登录账号
	private String loginAccount;
	// 客服类型 1 售前 2 售后
	private String serviceItemType;

}