package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 学员学车报名单
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_student_study_progress_history")
public class StudentStudyProgressHistoryEntity extends BaseEntity {


	// 主键唯一id.
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 学员ID
	private String studentId;

	// 科目类型
	private String testEnrollSubject;

	// 考试时间
	private LocalDateTime testEnrollTime;

	// 考试场地ID
	@TableField(fill = FieldFill.INSERT,value="test_coaching_grid_id")
	private String testCoachingGridId;

	// 考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）
	private String testResultType;

	// 时间抽排序
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

}