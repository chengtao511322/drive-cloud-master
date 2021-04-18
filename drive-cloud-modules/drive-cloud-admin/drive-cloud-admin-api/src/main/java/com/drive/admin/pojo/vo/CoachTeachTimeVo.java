package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 教练课程时间表
 *
 * @author guyi
 */
@Data
public class CoachTeachTimeVo {


	@Excel(name = "", width = 20)
	private String id;

	// 课程名称
	@Excel(name = "课程名称", width = 20)
	private String className;

	// 教练ID
	@Excel(name = "教练ID", width = 20)
	private String coachId;

	// 教练名称
	private String coachName;

	// 场地ID
	@Excel(name = "场地ID", width = 20)
	private String coachingGridId;

	// 学员ID
	@Excel(name = "学员ID", width = 20)
	private String studentId;

	// 驾照类型（c1；c2...）
	@Excel(name = "驾照类型（c1；c2...）", width = 20)
	private String driveType;

	// 科目类型
	@Excel(name = "科目类型", width = 20)
	private String subjectType;

	// 课时类型（1，普通训练，2考试训练）
	@Excel(name = "课时类型（1，普通训练，2考试训练）", width = 20)
	private String classType;

	// 课程日期
	@Excel(name = "课程日期", width = 20)
	private LocalDate classDate;

	// 开始时间
	@Excel(name = "开始时间", width = 20)
	private LocalDateTime startTime;

	// 结束时间
	@Excel(name = "结束时间", width = 20)
	private LocalDateTime endTime;

	// 学时
	@Excel(name = "学时", width = 20)
	private Integer classHours;

	// 状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)
	@Excel(name = "状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)", width = 20)
	private String status;

	// 平台提成百分比
	@Excel(name = "平台提成百分比", width = 20)
	private BigDecimal serviceChangePercent;

	// 教练提成百分比
	@Excel(name = "教练提成百分比", width = 20)
	private BigDecimal coachChargePercent;

	// 公车提成百分比
	@Excel(name = "公车提成百分比", width = 20)
	private BigDecimal carChargePercent;

	// 课时总费用
	@Excel(name = "课时总费用", width = 20)
	private BigDecimal sumCharge;

	// 平台提成金额
	@Excel(name = "平台提成金额", width = 20)
	private BigDecimal serviceCharge;

	// 教练提成金额
	@Excel(name = "教练提成金额", width = 20)
	private BigDecimal coaceCharge;

	// 公车提成金额
	@Excel(name = "公车提成金额", width = 20)
	private BigDecimal carCharge;

	// 备注
	@Excel(name = "备注", width = 20)
	private String remarks;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 上车时间
	@Excel(name = "上车时间", width = 20)
	private LocalDateTime aboardTime;

	// 接你的时间
	@Excel(name = "接你的时间", width = 20)
	private LocalDateTime takeTime;

	// 实际开始时间
	@Excel(name = "实际开始时间", width = 20)
	private LocalDateTime actualStartTime;

	// 实际结束时间
	@Excel(name = "实际结束时间", width = 20)
	private LocalDateTime actualEndTime;

	// 剩余课时
	@Excel(name = "剩余课时", width = 20)
	private Integer surplusClassHours;

	// 发布科目二场地ID
	@Excel(name = "发布科目二场地ID", width = 20)
	private String coachingGridId2;

	// 发布科目三场地ID
	@Excel(name = "发布科目三场地ID", width = 20)
	private String coachingGridId3;

	// 发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)
	@Excel(name = "发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)", width = 20)
	private String releaseSubjectType;

	// 课时预扣百分比
	@Excel(name = "课时预扣百分比", width = 20)
	private BigDecimal withholdPercent;

	// 课时预扣金额
	@Excel(name = "课时预扣金额", width = 20)
	private BigDecimal withholdCharge;

	// 是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)
	@Excel(name = "是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)", width = 20)
	private String isHandleWithhole;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 是否为预收入(0-否，1-是)
	@Excel(name = "是否为预收入(0-否，1-是)", width = 20)
	private String isExpect;

	// 预收入班型id
	@Excel(name = "预收入班型id", width = 20)
	private String expectClassId;
	 // 课时总和
	private int classHoursTotal;
	// 学员信息
	private StudentInfoVo studentVo;

	private String onlineServiceName;
	private String lineServiceName;


	private String provinceName;
	private String cityName;
	private String areaName;
	// 总课时
	private int TotalHour;


	private StudentTestEnrollVo studentTestEnrollVo;
}