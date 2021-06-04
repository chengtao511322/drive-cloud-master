package com.drive.system.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.common.core.tree.TreeVoFeature;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 部门
 *
 * @author xiaoguo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptVo implements TreeVoFeature<DeptVo> {


	// 部门id
	@ApiModelProperty(value = "部门id")
	private Long deptId;

	// 部门名称
	@ApiModelProperty(value = "部门名称")
	@Excel(name = "部门名称", width = 20)
	private String deptName;

	// 父部门id
	@ApiModelProperty(value = "父部门id")
	private Long parentId;

	// 父部门ids
	@ApiModelProperty(value = "父部门ids")
	private String parentIds;

	// 负责人
	@Excel(name = "负责人", width = 20)
	@ApiModelProperty(value = "负责人")
	private String leader;

	// 联系电话
	@Excel(name = "联系电话", width = 20)
	@ApiModelProperty(value = "联系电话")
	private String phone;

	// 邮箱
	@Excel(name = "邮箱", width = 20)
	@ApiModelProperty(value = "邮箱")
	private String email;

	// 描述
	@ApiModelProperty(value = "描述")
	private String description;

	// 备注
	@Excel(name = "备注", width = 20)
	@ApiModelProperty(value = "备注")
	private String remark;

	// 排序
	@Excel(name = "排序", width = 20)
	@ApiModelProperty(value = "排序")
	private Integer orderNum;

	// 状态（0正常 1停用）
	@ApiModelProperty(value = "状态（0正常 1停用）")
	private String status;

	// 创建人
	@ApiModelProperty(value = "创建人")
	private String createBy;

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	// 更新人
	@ApiModelProperty(value = "更新人")
	private String updateBy;

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	// 租户ID
	@ApiModelProperty(value = "租户id")
	private String tenantId;


	/** 子部门 */
	@ApiModelProperty(value = "字部门")
	private List<DeptVo> children = new ArrayList<>();

	public DeptVo(DeptVo deptVo) {

	}

	@Override
	public void putChildrenList(List<DeptVo> children) {
		this.children = children;
	}

	@Override
	public List<DeptVo> getChildrenList() {
		return this.children;
	}

	@Override
	public String findNodeId() {
		return String.valueOf(this.deptId);
	}

	@Override
	public String findParentNodeId() {
		return String.valueOf(this.parentId);
	}

	@Override
	public String getTreeLabel() {
		return this.getDeptName();
	}
}