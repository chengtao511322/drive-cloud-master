package com.drive.admin.pojo.entity;

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
import com.baomidou.mybatisplus.annotation.FieldFill;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 平台题库表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_question_bank")
public class QuestionBankEntity extends BaseEntity {


	private String id;

	// 题号
	private Integer questionNo;

	// 科目类型
	private String subjectType;

	// 题目章节
	private String questionChapter;

	// 题型（知识点类型）
	private String questionType;

	// 媒介路径（图片或者视频）
	private String mediaUrl;

	// 题目内容
	private String questionContent;

	// 是否多选
	private String isMultipleAnswer;

	// 答题限时（单位秒）
	private Integer answerTimeLimit;

	// 解答说明
	private String answerRemarks;

	// 创建者
	private String createUser;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新者
	private String updateUser;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 难度(1,2,3,4,5)
	private String difficulty;

	// 媒介类型(1-图片，2-视频)
	private String isView;

}