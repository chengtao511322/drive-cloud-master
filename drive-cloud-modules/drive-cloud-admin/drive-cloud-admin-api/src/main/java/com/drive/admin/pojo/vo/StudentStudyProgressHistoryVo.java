package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 学员学车报名单
 *
 * @author xiaoguo
 */
@Data
public class StudentStudyProgressHistoryVo {


	// 主键唯一id
	@Excel(name = "主键唯一id", width = 20)
	private String id;


	// 学员ID
	@Excel(name = "学员ID", width = 20)
	private String studentId;

	// 科目类型
	@Excel(name = "科目类型", width = 20)
	private String testEnrollSubject;

	// 考试时间
	@Excel(name = "考试时间", width = 20)
	private LocalDateTime testEnrollTime;

	// 考试场地ID
	@Excel(name = "考试场地ID", width = 20)
	private String testCoachingGridId;

	// 考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）
	@Excel(name = "考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）", width = 20)
	private String testResultType;

	// 时间抽排序
	@Excel(name = "时间抽排序", width = 20)
	private LocalDateTime createTime;

}