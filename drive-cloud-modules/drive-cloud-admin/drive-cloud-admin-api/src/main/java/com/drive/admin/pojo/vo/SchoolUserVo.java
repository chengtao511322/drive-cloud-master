package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 合作驾校用户
 *
 * @author xiaoguo
 */
@Data
public class SchoolUserVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 姓名
	@Excel(name = "姓名", width = 20)
	private String name;

	// 电话
	@Excel(name = "电话", width = 20)
	private String phone;

	// 登录密码
	@Excel(name = "登录密码", width = 20)
	private String password;

	// 账户所属驾校
	@Excel(name = "账户所属驾校", width = 20)
	private String schoolId;

	private String schoolName;

	// 状态(1-正常，2-停用)
	@Excel(name = "状态(1-正常，2-停用)", width = 20)
	private String status;

	// 用户类型(1-管理员，2-普通用户)
	@Excel(name = "用户类型(1-管理员，2-普通用户)", width = 20)
	private Integer userType;

	// token
	@Excel(name = "token", width = 20)
	private String token;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}