package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 客服回访记录
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ServiceReturnVisitHistoryPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	@ApiModelProperty(value = "回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）")
	private String returnVisitType;
	// 模糊查询字段

	// 订单明细单号
	@ApiModelProperty(value = "订单明细单号")
	private String orderDetailNo;
	// 模糊查询字段

	// 学员id
	@ApiModelProperty(value = "学员id")
	private String studentId;
	// 模糊查询字段

	// 回访时间
	@ApiModelProperty(value = "回访时间")
	private LocalDateTime returnVisitTime;
	// 模糊查询字段

	// 回访内容
	@ApiModelProperty(value = "回访内容")
	private String returnVisitContent;

	// 回访状态  1：售前回访 2 售后回访
	private String returnVisitStatus;
	// 模糊查询字段

	// 预计下次回访时间
	@ApiModelProperty(value = "预计下次回访时间")
	private LocalDateTime nextReturnVisitTime;
	// 模糊查询字段

	// 客服id
	@ApiModelProperty(value = "客服id")
	private String serviceId;
	// 模糊查询字段

	// 是否完结（是，否）
	@ApiModelProperty(value = "是否完结（是，否）")
	private String isEnd;
	// 模糊查询字段

	// 科目类型(1-科目一，2-科目三，3-科目三，4-科目四)
	@ApiModelProperty(value = "科目类型(1-科目一，2-科目三，3-科目三，4-科目四)")
	private String subjectType;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	//private String vagueNameSearch

}