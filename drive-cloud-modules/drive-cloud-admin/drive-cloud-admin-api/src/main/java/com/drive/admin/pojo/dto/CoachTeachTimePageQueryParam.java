package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 教练课程时间表
 *
 * @author guyi
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CoachTeachTimePageQueryParam extends BasePageQueryParam {


	@ApiModelProperty(value = "")
	private String id;
	// 模糊查询字段

	// 课程名称
	@ApiModelProperty(value = "课程名称")
	private String className;
	// 模糊查询字段

	// 教练ID
	@ApiModelProperty(value = "教练ID")
	private String coachId;
	// 模糊查询字段

	// 场地ID
	@ApiModelProperty(value = "场地ID")
	private String coachingGridId;
	// 模糊查询字段

	// 学员ID
	@ApiModelProperty(value = "学员ID")
	private String studentId;
	// 模糊查询字段

	// 驾照类型（c1；c2...）
	@ApiModelProperty(value = "驾照类型（c1；c2...）")
	private String driveType;
	// 模糊查询字段

	// 科目类型
	@ApiModelProperty(value = "科目类型")
	private String subjectType;
	// 模糊查询字段

	// 课时类型（1，普通训练，2考试训练）
	@ApiModelProperty(value = "课时类型（1，普通训练，2考试训练）")
	private String classType;
	// 模糊查询字段

	// 课程日期
	@ApiModelProperty(value = "课程日期")
	private LocalDate classDate;
	// 模糊查询字段

	// 开始时间
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startTime;
	// 模糊查询字段

	// 结束时间
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	// 模糊查询字段

	// 学时
	@ApiModelProperty(value = "学时")
	private Integer classHours;
	// 模糊查询字段

	// 状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)
	@ApiModelProperty(value = "状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)")
	private String status;
	// 模糊查询字段

	// 平台提成百分比
	@ApiModelProperty(value = "平台提成百分比")
	private BigDecimal serviceChangePercent;
	// 模糊查询字段

	// 教练提成百分比
	@ApiModelProperty(value = "教练提成百分比")
	private BigDecimal coachChargePercent;
	// 模糊查询字段

	// 公车提成百分比
	@ApiModelProperty(value = "公车提成百分比")
	private BigDecimal carChargePercent;
	// 模糊查询字段

	// 课时总费用
	@ApiModelProperty(value = "课时总费用")
	private BigDecimal sumCharge;
	// 模糊查询字段

	// 平台提成金额
	@ApiModelProperty(value = "平台提成金额")
	private BigDecimal serviceCharge;
	// 模糊查询字段

	// 教练提成金额
	@ApiModelProperty(value = "教练提成金额")
	private BigDecimal coaceCharge;
	// 模糊查询字段

	// 公车提成金额
	@ApiModelProperty(value = "公车提成金额")
	private BigDecimal carCharge;
	// 模糊查询字段

	// 备注
	@ApiModelProperty(value = "备注")
	private String remarks;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 上车时间
	@ApiModelProperty(value = "上车时间")
	private LocalDateTime aboardTime;
	// 模糊查询字段

	// 接你的时间
	@ApiModelProperty(value = "接你的时间")
	private LocalDateTime takeTime;
	// 模糊查询字段

	// 实际开始时间
	@ApiModelProperty(value = "实际开始时间")
	private LocalDateTime actualStartTime;
	// 模糊查询字段

	// 实际结束时间
	@ApiModelProperty(value = "实际结束时间")
	private LocalDateTime actualEndTime;
	// 模糊查询字段

	// 剩余课时
	@ApiModelProperty(value = "剩余课时")
	private Integer surplusClassHours;
	// 模糊查询字段

	// 发布科目二场地ID
	@ApiModelProperty(value = "发布科目二场地ID")
	private String coachingGridId2;
	// 模糊查询字段

	// 发布科目三场地ID
	@ApiModelProperty(value = "发布科目三场地ID")
	private String coachingGridId3;
	// 模糊查询字段

	// 发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)
	@ApiModelProperty(value = "发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)")
	private String releaseSubjectType;
	// 模糊查询字段

	// 课时预扣百分比
	@ApiModelProperty(value = "课时预扣百分比")
	private BigDecimal withholdPercent;
	// 模糊查询字段

	// 课时预扣金额
	@ApiModelProperty(value = "课时预扣金额")
	private BigDecimal withholdCharge;
	// 模糊查询字段

	// 是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)
	@ApiModelProperty(value = "是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)")
	private String isHandleWithhole;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段

	// 是否为预收入(0-否，1-是)
	@ApiModelProperty(value = "是否为预收入(0-否，1-是)")
	private String isExpect;
	// 模糊查询字段

	// 预收入班型id
	@ApiModelProperty(value = "预收入班型id")
	private String expectClassId;
	// 模糊查询字段
	//private String vagueNameSearch

}