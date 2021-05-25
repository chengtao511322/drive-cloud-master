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
 * 字典表
 *
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CodePageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String codeId;
	// 模糊查询字段

	// 分类
	@ApiModelProperty(value = "分类")
	private String category;
	// 模糊查询字段

	// 值
	@ApiModelProperty(value = "值")
	private String codeValue;
	// 模糊查询字段

	// 显示项
	@ApiModelProperty(value = "显示项")
	private String disText;
	// 模糊查询字段

	// 标签
	@ApiModelProperty(value = "标签")
	private String tags;
	// 模糊查询字段

	// 排序
	@ApiModelProperty(value = "排序")
	private String odBy;
	// 模糊查询字段

	// 备注
	@ApiModelProperty(value = "备注")
	private String remarks;
	// 模糊查询字段

	// 父项
	@ApiModelProperty(value = "父项")
	private String supCodeId;
	// 模糊查询字段

	// 创建人
	@ApiModelProperty(value = "创建人")
	private String createUser;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新人
	@ApiModelProperty(value = "更新人")
	private String updateUser;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段
	//private String vagueNameSearch

}