package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 运营商参数设置表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OperatorSettinngPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 编号(使用 00000 的形式来添加)
	@ApiModelProperty(value = "编号(使用 00000 的形式来添加)")
	private String number;
	// 模糊查询字段

	// 编号对应值
	@ApiModelProperty(value = "编号对应值")
	private String numberValue;
	// 模糊查询字段

	// 编号中文描述
	@ApiModelProperty(value = "编号中文描述")
	private String numberDescribe;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段

	// 状态(1-正常，2-停用)
	@ApiModelProperty(value = "状态(1-正常，2-停用)")
	private String status;
	// 模糊查询字段

	// 是否删除（0-否，1-是）
	@ApiModelProperty(value = "是否删除（0-否，1-是）")
	private String isDelete;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段
	private String vagueNumberDescribeSearch;

	private String[] createDateTimeSearchArr = new String[2];

}