package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 平台题目选项
 *
 * @author chentao
 */
@Data
public class QuestionOptionsVo {


	@Excel(name = "", width = 20)
	private String id;

	@Excel(name = "题号",width = 20)
	private String questionNo;

	// 题库ID
	@Excel(name = "题库ID", width = 20)
	private String questionBankId;

	// 题目选项
	@Excel(name = "题目选项", width = 20)
	private String questionOption;

	// 选项内容
	@Excel(name = "选项内容", width = 20)
	private String optionContent;

	// 是否正确
	@Excel(name = "是否正确", width = 20)
	private String isRight;

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



}