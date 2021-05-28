package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 平台题库表
 *
 * @author chentao
 */
@Data
public class QuestionBankVo {


	@Excel(name = "", width = 20)
	private String id;

	// 题号
	@Excel(name = "题号", width = 20)
	private Integer questionNo;

	// 科目类型
	@Excel(name = "科目类型", width = 20)
	private String subjectType;

	// 题目章节
	@Excel(name = "题目章节", width = 20)
	private String questionChapter;

	// 题型（知识点类型）
	@Excel(name = "题型（知识点类型）", width = 20)
	private String questionType;

	// 媒介路径（图片或者视频）
	@Excel(name = "媒介路径（图片或者视频）", width = 20)
	private String mediaUrl;

	// 题目内容
	@Excel(name = "题目内容", width = 20)
	private String questionContent;

	// 是否多选
	@Excel(name = "是否多选", width = 20)
	private String isMultipleAnswer;

	// 答题限时（单位秒）
	@Excel(name = "答题限时（单位秒）", width = 20)
	private Integer answerTimeLimit;

	// 解答说明
	@Excel(name = "解答说明", width = 20)
	private String answerRemarks;

	// 创建者
	@Excel(name = "创建者", width = 20)
	private String createUser;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新者
	@Excel(name = "更新者", width = 20)
	private String updateUser;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 难度(1,2,3,4,5)
	@Excel(name = "难度(1,2,3,4,5)", width = 20)
	private String difficulty;

	// 媒介类型(1-图片，2-视频)
	@Excel(name = "媒介类型(1-图片，2-视频)", width = 20)
	private String isView;

}