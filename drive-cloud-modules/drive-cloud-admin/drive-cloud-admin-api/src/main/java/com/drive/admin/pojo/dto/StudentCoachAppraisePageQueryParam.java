package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 学员教练互评表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class StudentCoachAppraisePageQueryParam extends BasePageQueryParam {


	// 编号
	@ApiModelProperty(value = "编号")
	private String id;
	// 模糊查询字段

	// 订单号
	@ApiModelProperty(value = "订单号")
	private String orderNo;
	// 模糊查询字段

	// 教练课程ID
	@ApiModelProperty(value = "教练课程ID")
	private String classId;
	// 模糊查询字段

	// 报名单号（考试，学车）
	@ApiModelProperty(value = "报名单号（考试，学车）")
	private String enrollNo;
	// 模糊查询字段

	// 评价用户id（学员id）
	@ApiModelProperty(value = "评价用户id（学员id）")
	private String studentId;
	// 模糊查询字段

	// 学员评价内容
	@ApiModelProperty(value = "学员评价内容")
	private String appraiseReplyContent;
	// 模糊查询字段

	// 学员评价分数
	@ApiModelProperty(value = "学员评价分数")
	private BigDecimal appraiseReplyGrade;
	// 模糊查询字段

	// 学员评价时间
	@ApiModelProperty(value = "学员评价时间")
	private LocalDateTime appraiseReplyTime;
	// 模糊查询字段

	// 回复内容
	@ApiModelProperty(value = "回复内容")
	private String replyContent;
	// 模糊查询字段

	// 回复时间
	@ApiModelProperty(value = "回复时间")
	private LocalDateTime replyTime;
	// 模糊查询字段

	// 被评价用户id
	@ApiModelProperty(value = "被评价用户id")
	private String replyId;
	// 模糊查询字段

	// 被评价用户类型（2-教练；3-运维）
	@ApiModelProperty(value = "被评价用户类型（2-教练；3-运维）")
	private String replyType;
	// 模糊查询字段

	// 被评价用户头像
	@ApiModelProperty(value = "被评价用户头像")
	private String replyHeadUrl;
	// 模糊查询字段

	// 学员头像
	@ApiModelProperty(value = "学员头像")
	private String studentHeadUrl;
	// 模糊查询字段

	// 学员评价类型（1-评价 2-投诉）
	@ApiModelProperty(value = "学员评价类型（1-评价 2-投诉）")
	private String appraiseType;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	private String vagueOrderNoSearch;
	// 模糊查询
	private String vagueEnrollNoSearch;
	//
	private String vagueAppraiseReplyContentSearch;
	//
	private String vagueClassIdSearch;
	// 时间查询
	private String searchAppraiseReplyTime;
	private String searchReplyTime;

}