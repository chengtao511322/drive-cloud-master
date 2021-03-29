package com.drive.admin.pojo.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 学员教练互评表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_student_coach_appraise")
public class StudentCoachAppraiseEntity extends BaseEntity {


	// 编号
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 订单号
	private String orderNo;

	// 教练课程ID
	private String classId;

	// 报名单号（考试，学车）
	private String enrollNo;

	// 评价用户id（学员id）
	private String studentId;

	// 学员评价内容
	private String appraiseReplyContent;

	// 学员评价分数
	private BigDecimal appraiseReplyGrade;

	// 学员评价时间
	private LocalDateTime appraiseReplyTime;

	// 回复内容
	private String replyContent;


	// 回复时间
	private LocalDateTime replyTime;

	// 被评价用户id
	private String replyId;

	// 被评价用户类型（2-教练；3-运维）
	private String replyType;

	// 被评价用户头像
	private String replyHeadUrl;

	// 学员头像
	private String studentHeadUrl;

	// 学员评价类型（1-评价 2-投诉）
	private String appraiseType;

	// 运营商id(数据权限标记)
	private String operatorId;

}