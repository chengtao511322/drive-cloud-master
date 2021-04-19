package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 学员学车报名单
 *
 * @author xiaoguo
 */
@Data
public class StudentStudyProgressHistoryInstallParam {


    // 主键唯一id
    @ApiModelProperty(value = "主键唯一id")
    private String id;

    // 学员ID
    @NotBlank(message = "学员ID不能为空")
    @ApiModelProperty(value = "学员ID")
    private String studentId;

    // 科目类型
    @NotBlank(message = "科目类型不能为空")
    @ApiModelProperty(value = "科目类型")
    private String testEnrollSubject;

    // 考试时间
    @NotBlank(message = "考试时间不能为空")
    @ApiModelProperty(value = "考试时间")
    private LocalDateTime testEnrollTime;

    // 考试场地ID
    @NotBlank(message = "考试场地ID不能为空")
    @ApiModelProperty(value = "考试场地ID")
    private String testCoachingGridId;

    // 考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）
    @NotBlank(message = "考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）不能为空")
    @ApiModelProperty(value = "考试结果类型（1：考试预约成功；2：考试通过；3考试不通过）")
    private String testResultType;

    // 时间抽排序
    @ApiModelProperty(value = "时间抽排序")
    private LocalDateTime createTime;


}