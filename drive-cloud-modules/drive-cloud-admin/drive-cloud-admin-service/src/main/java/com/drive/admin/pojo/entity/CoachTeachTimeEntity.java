package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 教练课程时间表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author guyi
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_coach_teach_time")
public class CoachTeachTimeEntity extends BaseEntity {


	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 课程名称
	private String className;

	// 教练ID
	private String coachId;

	// 场地ID
	private String coachingGridId;
	// 学员ID
	private String studentId;

	// 驾照类型（c1；c2...）
	private String driveType;

	// 科目类型
	private String subjectType;

	// 课时类型（1，普通训练，2考试训练）
	private String classType;

	// 课程日期
	private LocalDate classDate;

	// 开始时间
	private LocalDateTime startTime;

	// 结束时间
	private LocalDateTime endTime;

	// 学时
	private Integer classHours;

	// 状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)
	private String status;

	// 平台提成百分比
	private BigDecimal serviceChangePercent;

	// 教练提成百分比
	private BigDecimal coachChargePercent;

	// 公车提成百分比
	private BigDecimal carChargePercent;

	// 课时总费用
	private BigDecimal sumCharge;

	// 平台提成金额
	private BigDecimal serviceCharge;

	// 教练提成金额
	private BigDecimal coaceCharge;

	// 公车提成金额
	private BigDecimal carCharge;

	// 备注
	private String remarks;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 上车时间
	private LocalDateTime aboardTime;

	// 接你的时间
	private LocalDateTime takeTime;

	// 实际开始时间
	private LocalDateTime actualStartTime;

	// 实际结束时间
	private LocalDateTime actualEndTime;

	// 剩余课时
	private Integer surplusClassHours;

	// 发布科目二场地ID
	@TableField(value="coaching_grid_id_2")
	private String coachingGridId2;

	// 发布科目三场地ID
	@TableField(value="coaching_grid_id_3")
	private String coachingGridId3;

	// 发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)
	private String releaseSubjectType;

	// 课时预扣百分比
	private BigDecimal withholdPercent;

	// 课时预扣金额
	private BigDecimal withholdCharge;

	// 是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)
	private String isHandleWithhole;

	// 运营商id(数据权限标记)
	private String operatorId;

	private String expectClassId;
	// 是否为预收入(0-否，1-是)
	private String isExpect;
}