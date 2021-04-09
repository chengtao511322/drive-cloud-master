package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 教练课程时间表
 *
 * @author guyi
 */
@Data
public class CoachTeachTimeEditParam {


    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String id;

    // 课程名称
    @NotBlank(message = "课程名称不能为空")
    @ApiModelProperty(value = "课程名称")
    private String className;

    // 教练ID
    @NotBlank(message = "教练ID不能为空")
    @ApiModelProperty(value = "教练ID")
    private String coachId;

    // 场地ID
    @NotBlank(message = "场地ID不能为空")
    @ApiModelProperty(value = "场地ID")
    private String coachingGridId;

    // 学员ID
    @NotBlank(message = "学员ID不能为空")
    @ApiModelProperty(value = "学员ID")
    private String studentId;

    // 驾照类型（c1；c2...）
    @NotBlank(message = "驾照类型（c1；c2...）不能为空")
    @ApiModelProperty(value = "驾照类型（c1；c2...）")
    private String driveType;

    // 科目类型
    @NotBlank(message = "科目类型不能为空")
    @ApiModelProperty(value = "科目类型")
    private String subjectType;

    // 课时类型（1，普通训练，2考试训练）
    @NotBlank(message = "课时类型（1，普通训练，2考试训练）不能为空")
    @ApiModelProperty(value = "课时类型（1，普通训练，2考试训练）")
    private String classType;

    // 课程日期
    @NotBlank(message = "课程日期不能为空")
    @ApiModelProperty(value = "课程日期")
    private LocalDate classDate;

    // 开始时间
    @NotBlank(message = "开始时间不能为空")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;

    // 结束时间
    @NotBlank(message = "结束时间不能为空")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    // 学时
    @NotBlank(message = "学时不能为空")
    @ApiModelProperty(value = "学时")
    private Integer classHours;

    // 状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)
    @NotBlank(message = "状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)不能为空")
    @ApiModelProperty(value = "状态(1-未预约；2-已预约；3-教学中；4-教学完成;5-已取消   6 接人中 7 已上车 8-超时取消)")
    private String status;

    // 平台提成百分比
    @NotBlank(message = "平台提成百分比不能为空")
    @ApiModelProperty(value = "平台提成百分比")
    private BigDecimal serviceChangePercent;

    // 教练提成百分比
    @NotBlank(message = "教练提成百分比不能为空")
    @ApiModelProperty(value = "教练提成百分比")
    private BigDecimal coachChargePercent;

    // 公车提成百分比
    @NotBlank(message = "公车提成百分比不能为空")
    @ApiModelProperty(value = "公车提成百分比")
    private BigDecimal carChargePercent;

    // 课时总费用
    @NotBlank(message = "课时总费用不能为空")
    @ApiModelProperty(value = "课时总费用")
    private BigDecimal sumCharge;

    // 平台提成金额
    @NotBlank(message = "平台提成金额不能为空")
    @ApiModelProperty(value = "平台提成金额")
    private BigDecimal serviceCharge;

    // 教练提成金额
    @NotBlank(message = "教练提成金额不能为空")
    @ApiModelProperty(value = "教练提成金额")
    private BigDecimal coaceCharge;

    // 公车提成金额
    @NotBlank(message = "公车提成金额不能为空")
    @ApiModelProperty(value = "公车提成金额")
    private BigDecimal carCharge;

    // 备注
    @NotBlank(message = "备注不能为空")
    @ApiModelProperty(value = "备注")
    private String remarks;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @NotBlank(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 上车时间
    @NotBlank(message = "上车时间不能为空")
    @ApiModelProperty(value = "上车时间")
    private LocalDateTime aboardTime;

    // 接你的时间
    @NotBlank(message = "接你的时间不能为空")
    @ApiModelProperty(value = "接你的时间")
    private LocalDateTime takeTime;

    // 实际开始时间
    @NotBlank(message = "实际开始时间不能为空")
    @ApiModelProperty(value = "实际开始时间")
    private LocalDateTime actualStartTime;

    // 实际结束时间
    @NotBlank(message = "实际结束时间不能为空")
    @ApiModelProperty(value = "实际结束时间")
    private LocalDateTime actualEndTime;

    // 剩余课时
    @NotBlank(message = "剩余课时不能为空")
    @ApiModelProperty(value = "剩余课时")
    private Integer surplusClassHours;

    // 发布科目二场地ID
    @NotBlank(message = "发布科目二场地ID不能为空")
    @ApiModelProperty(value = "发布科目二场地ID")
    private String coachingGridId2;

    // 发布科目三场地ID
    @NotBlank(message = "发布科目三场地ID不能为空")
    @ApiModelProperty(value = "发布科目三场地ID")
    private String coachingGridId3;

    // 发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)
    @NotBlank(message = "发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)不能为空")
    @ApiModelProperty(value = "发布课程-科目类型(1-科目一,2-科目二,3-科目三,4-科目四,5-科二/三)")
    private String releaseSubjectType;

    // 课时预扣百分比
    @NotBlank(message = "课时预扣百分比不能为空")
    @ApiModelProperty(value = "课时预扣百分比")
    private BigDecimal withholdPercent;

    // 课时预扣金额
    @NotBlank(message = "课时预扣金额不能为空")
    @ApiModelProperty(value = "课时预扣金额")
    private BigDecimal withholdCharge;

    // 是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)
    @NotBlank(message = "是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)不能为空")
    @ApiModelProperty(value = "是否已经处理预扣金额(防止学员二次挂科后处理，获取到历史数据)")
    private String isHandleWithhole;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;

    // 是否为预收入(0-否，1-是)
    @NotBlank(message = "是否为预收入(0-否，1-是)不能为空")
    @ApiModelProperty(value = "是否为预收入(0-否，1-是)")
    private String isExpect;

    // 预收入班型id
    @NotBlank(message = "预收入班型id不能为空")
    @ApiModelProperty(value = "预收入班型id")
    private String expectClassId;


}