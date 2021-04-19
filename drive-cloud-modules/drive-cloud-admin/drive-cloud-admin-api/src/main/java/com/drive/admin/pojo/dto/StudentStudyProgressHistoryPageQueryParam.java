package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 学员学车报名单
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class StudentStudyProgressHistoryPageQueryParam extends BasePageQueryParam {


	// 主键唯一id
	@ApiModelProperty(value = "主键唯一id")
	private String id;
	// 模糊查询字段

	// 学员ID
	@ApiModelProperty(value = "学员ID")
	private String studentId;
	// 模糊查询字段

	// 科目类型
	@ApiModelProperty(value = "科目类型")
	private String testEnrollSubject;
	// 模糊查询字段

	// 考试时间
	@ApiModelProperty(value = "考试时间")
	private LocalDateTime testEnrollTime;
	// 模糊查询字段

	// 考试场地ID
	@ApiModelProperty(value = "考试场地ID")
	private String testCoachingGridId;
	// 模糊查询字段

	// 考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）
	@ApiModelProperty(value = "考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）")
	private String testResultType;
	// 模糊查询字段

	// 时间抽排序
	@ApiModelProperty(value = "时间抽排序")
	private LocalDateTime createTime;
	// 模糊查询字段
	//private String vagueNameSearch

}