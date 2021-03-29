package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 合作驾校用户
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SchoolUserPageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 姓名
	@ApiModelProperty(value = "姓名")
	private String name;
	// 模糊查询字段

	// 电话
	@ApiModelProperty(value = "电话")
	private String phone;
	// 模糊查询字段

	// 登录密码
	@ApiModelProperty(value = "登录密码")
	private String password;
	// 模糊查询字段

	// 账户所属驾校
	@ApiModelProperty(value = "账户所属驾校")
	private String schoolId;
	// 模糊查询字段

	// 状态(1-正常，2-停用)
	@ApiModelProperty(value = "状态(1-正常，2-停用)")
	private String status;
	// 模糊查询字段

	// 用户类型(1-管理员，2-普通用户)
	@ApiModelProperty(value = "用户类型(1-管理员，2-普通用户)")
	private Integer userType;
	// 模糊查询字段

	// token
	@ApiModelProperty(value = "token")
	private String token;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	//private String vagueNameSearch

}