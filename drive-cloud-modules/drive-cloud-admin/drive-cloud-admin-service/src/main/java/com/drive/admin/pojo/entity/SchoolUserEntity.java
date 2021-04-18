package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 合作驾校用户
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_school_user")
public class SchoolUserEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 姓名
	private String name;

	// 电话
	private String phone;

	// 登录密码
	private String password;

	// 账户所属驾校
	private String schoolId;

	// 状态(1-正常，2-停用)
	private String status;

	// 用户类型(1-管理员，2-普通用户)
	private Integer userType;

	// token
	private String token;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	private String operatorId;

}