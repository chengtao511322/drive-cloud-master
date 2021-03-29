package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 评价标签表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class EvaluateTagPageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 标签名称
	@ApiModelProperty(value = "标签名称")
	private String name;
	// 模糊查询字段

	// 状态(1-正常，2-停用)
	@ApiModelProperty(value = "状态(1-正常，2-停用)")
	private String status;
	// 模糊查询字段

	// 订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	@ApiModelProperty(value = "订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）")
	private String orderType;
	// 模糊查询字段

	// 评价分段（1-好评（45星），2-中评（3星），3-差评（12星））
	@ApiModelProperty(value = "评价分段（1-好评（45星），2-中评（3星），3-差评（12星））")
	private String fractionParagraph;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 是否删除(0否；1：是)
	@ApiModelProperty(value = "是否删除(0否；1：是)")
	private String isDelete;
	// 模糊查询字段
	private String vagueNameSearch;

}