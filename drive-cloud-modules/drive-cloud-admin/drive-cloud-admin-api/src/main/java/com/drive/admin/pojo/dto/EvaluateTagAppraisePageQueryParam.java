package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 学员教练评价明细表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class EvaluateTagAppraisePageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 评价标签id
	@ApiModelProperty(value = "评价标签id")
	private String evaluateTagId;
	// 模糊查询字段

	// 学员教练评价表id
	@ApiModelProperty(value = "学员教练评价表id")
	private String studentCoachAppraiseId;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 是否删除
	@ApiModelProperty(value = "是否删除")
	private String isDelete;
	// 模糊查询字段
	//private String vagueNameSearch

}