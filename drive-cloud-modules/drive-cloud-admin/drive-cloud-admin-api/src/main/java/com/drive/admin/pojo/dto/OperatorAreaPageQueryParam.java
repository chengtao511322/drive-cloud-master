package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 运营商代理区域
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OperatorAreaPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 代理商id
	@ApiModelProperty(value = "代理商id")
	private String operatorId;
	// 模糊查询字段

	// 省级编码
	@ApiModelProperty(value = "省级编码")
	private String provinceId;
	// 模糊查询字段

	// 市级编码
	@ApiModelProperty(value = "市级编码")
	private String cityId;
	// 模糊查询字段

	// 区(县)级编码
	@ApiModelProperty(value = "区(县)级编码")
	private String areaId;
	// 模糊查询字段

	// 是否向上查询（价格,教练在当前区划查询不到时）
	@ApiModelProperty(value = "是否向上查询（价格,教练在当前区划查询不到时）")
	private String isUpSelect;
	// 模糊查询字段

	// 是否删除
	@ApiModelProperty(value = "是否删除")
	private Integer isDelete;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段
	//private String vagueNameSearch


}