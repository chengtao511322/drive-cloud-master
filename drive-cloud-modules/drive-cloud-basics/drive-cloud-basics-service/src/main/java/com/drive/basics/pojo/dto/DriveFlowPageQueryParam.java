package com.drive.basics.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 流程信息管理
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class DriveFlowPageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 标题
	@ApiModelProperty(value = "标题")
	private String title;
	// 模糊查询字段

	@ApiModelProperty(value = "")
	private String context;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 删除状态 0未删除  1 ：删除
	@ApiModelProperty(value = "删除状态 0未删除  1 ：删除")
	private Integer isDelete;
	// 模糊查询字段

	// 启用状态 0  未启用 1 已经启用
	@ApiModelProperty(value = "启用状态 0  未启用 1 已经启用")
	private Integer status;
	// 模糊查询字段

	// 栏目ID
	@ApiModelProperty(value = "栏目ID")
	private String channelId;
	// 模糊查询字段

	// 排序
	@ApiModelProperty(value = "排序")
	private Integer sort;
	// 模糊查询字段

	// 添加者
	@ApiModelProperty(value = "添加者")
	private String addUser;
	// 模糊查询字段

	// 运营位置 ，存放省市区编码，多个用逗号隔开
	@ApiModelProperty(value = "运营位置 ，存放省市区编码，多个用逗号隔开")
	private String operatingPosition;
	// 模糊查询字段

	// 租户ID
	@ApiModelProperty(value = "租户ID")
	private String tenantId;
	// 模糊查询字段
	private String vagueTitleSearch;

	// 类型 1 学车流程 2 平台承诺
	private Integer type;

}