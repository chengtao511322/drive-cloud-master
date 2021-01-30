package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 学员教练互评表
 *
 * @author xiaoguo
 */
@Data
public class StudentCoachAppraiseVo {


	// 编号
	@Excel(name = "编号", width = 20)
	private String id;

	// 订单号
	@Excel(name = "订单号", width = 20)
	private String orderNo;

	// 教练课程ID
	@Excel(name = "教练课程ID", width = 20)
	private String classId;

	// 报名单号（考试，学车）
	@Excel(name = "报名单号（考试，学车）", width = 20)
	private String enrollNo;

	// 评价用户id（学员id）
	@Excel(name = "评价用户id（学员id）", width = 20)
	private String studentId;

	// 学员评价内容
	@Excel(name = "学员评价内容", width = 20)
	private String appraiseReplyContent;

	// 学员评价分数
	@Excel(name = "学员评价分数", width = 20)
	private BigDecimal appraiseReplyGrade;

	// 学员评价时间
	@Excel(name = "学员评价时间", width = 20)
	private LocalDateTime appraiseReplyTime;

	// 回复内容
	@Excel(name = "回复内容", width = 20)
	private String replyContent;

	// 回复时间
	@Excel(name = "回复时间", width = 20)
	private LocalDateTime replyTime;

	// 被评价用户id
	@Excel(name = "被评价用户id", width = 20)
	private String replyId;

	// 被评价用户类型（2-教练；3-运维）
	@Excel(name = "被评价用户类型（2-教练；3-运维）", width = 20)
	private String replyType;

	// 被评价用户头像
	@Excel(name = "被评价用户头像", width = 20)
	private String replyHeadUrl;

	// 学员头像
	@Excel(name = "学员头像", width = 20)
	private String studentHeadUrl;

	// 学员评价类型（1-评价 2-投诉）
	@Excel(name = "学员评价类型（1-评价 2-投诉）", width = 20)
	private String appraiseType;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}