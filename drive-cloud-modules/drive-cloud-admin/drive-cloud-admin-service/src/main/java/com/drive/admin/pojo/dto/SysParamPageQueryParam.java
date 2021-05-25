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
 * 系统配置参数表
 *
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class SysParamPageQueryParam extends BasePageQueryParam {


	// 参数枚举编号
	@ApiModelProperty(value = "参数枚举编号")
	private String prmEnumId;
	// 模糊查询字段

	// 参数名称
	@ApiModelProperty(value = "参数名称")
	private String prmName;
	// 模糊查询字段

	// 参数中文涵义
	@ApiModelProperty(value = "参数中文涵义")
	private String prmCnName;
	// 模糊查询字段

	// 参数数值
	@ApiModelProperty(value = "参数数值")
	private String prmValue;
	// 模糊查询字段

	// 参数备注
	@ApiModelProperty(value = "参数备注")
	private String prmRemark;
	// 模糊查询字段

	// 参数状态，0-正常 1-停用
	@ApiModelProperty(value = "参数状态，0-正常 1-停用")
	private String prmStatus;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 创建人
	@ApiModelProperty(value = "创建人")
	private String createUser;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 修改人
	@ApiModelProperty(value = "修改人")
	private String updateUser;
	// 模糊查询字段
	//private String vagueNameSearch

	private String vaguePrmNameSearch;

}