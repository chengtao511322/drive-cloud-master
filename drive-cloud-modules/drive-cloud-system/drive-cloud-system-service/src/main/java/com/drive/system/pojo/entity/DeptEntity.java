package com.drive.system.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 部门
 *
 * @author xiaoguo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dept")
public class DeptEntity extends BaseEntity {


	// 部门id
	@TableId(value = "dept_id" , type = IdType.AUTO)
	private Long deptId;

	// 部门名称
	private String deptName;

	// 父部门id
	private Long parentId;

	// 父部门ids
	private String parentIds;

	// 负责人
	private String leader;

	// 联系电话
	private String phone;

	// 邮箱
	private String email;

	// 描述
	private String description;

	// 备注
	private String remark;

	// 排序
	private Integer orderNum;

	// 状态（0正常 1停用）
	private String status;

	// 租户ID
	private String tenantId;

}