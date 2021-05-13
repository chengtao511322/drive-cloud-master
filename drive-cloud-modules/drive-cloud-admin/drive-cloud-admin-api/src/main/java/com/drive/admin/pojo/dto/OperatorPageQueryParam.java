package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 运营商基础信息
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OperatorPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 运营商名称
	@ApiModelProperty(value = "运营商名称")
	private String name;
	// 模糊查询字段

	// 运营商级别(1-省，2-市，3-区(县))
	@ApiModelProperty(value = "运营商级别(1-省，2-市，3-区(县))")
	private Integer level;
	// 模糊查询字段

	// 平台提成百分比
	@ApiModelProperty(value = "平台提成百分比")
	private BigDecimal platformChargePercent;
	// 模糊查询字段

	// 是否删除
	@ApiModelProperty(value = "是否删除")
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

	// 使用状态（1：正常；2：停用）
	@ApiModelProperty(value = "使用状态（1：正常；2：停用）")
	private String status;
	// 模糊查询字段

	// 父级代理商id
	@ApiModelProperty(value = "父级代理商id")
	private String parentId;
	// 模糊查询字段

	// 分配课时绩效比例
	@ApiModelProperty(value = "分配课时绩效比例")
	private BigDecimal hourPerformanceRatio;
	// 模糊查询字段

	// 其他benaid
	@ApiModelProperty(value = "其他benaid")
	private String channelBeanId;
	// 模糊查询字段
	private String vagueNameSearch;

	private String[] createDateTimeSearchArr = new String[2];

}