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
 * 教练授课区域表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CoachGiveAreaPageQueryParam extends BasePageQueryParam {


	@ApiModelProperty(value = "")
	private String id;
	// 模糊查询字段

	// 教练ID
	@ApiModelProperty(value = "教练ID")
	private String coachId;
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
	private Integer isUpSelect;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	// 模糊查询字段
	//private String vagueNameSearch

}