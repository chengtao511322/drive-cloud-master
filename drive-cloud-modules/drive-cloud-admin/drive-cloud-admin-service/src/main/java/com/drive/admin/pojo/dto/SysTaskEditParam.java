package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * 系统任务表
 *
 * @author chentao
 */
@Data
public class SysTaskEditParam {


    // 任务编号
    @NotBlank(message = "任务编号不能为空")
    @ApiModelProperty(value = "任务编号")
    private String stTaskId;

    // 任务名称
    @NotBlank(message = "任务名称不能为空")
    @ApiModelProperty(value = "任务名称")
    private String stTaskName;

    // 任务类
    @NotBlank(message = "任务类不能为空")
    @ApiModelProperty(value = "任务类")
    private String stJobClass;

    // CRON表达式
    @NotBlank(message = "CRON表达式不能为空")
    @Pattern(regexp = SysTaskInstallParam.cronRegEx,message = "非法的cron表达式")
    @ApiModelProperty(value = "CRON表达式")
    private String stCronExpression;

    // 状态
    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "启动时是否加载状态 0-否 1-是")
    private String stState;

    // 记入日志类型
    @NotBlank(message = "记入日志类型不能为空")
    @ApiModelProperty(value = "记入日志类型")
    private String stInputLogType;

    // 备注
    @ApiModelProperty(value = "备注")
    private String stRemark;

    // 创建人
    @ApiModelProperty(value = "创建人")
    private String createUser;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 修改人
    @ApiModelProperty(value = "修改人")
    private String updateUser;


}