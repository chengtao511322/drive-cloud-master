package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * 系统配置参数表
 *
 * @author chentao
 */
@Data
public class SysParamEditParam {


    // 参数枚举编号
    @NotBlank(message = "参数枚举编号不能为空")
    @ApiModelProperty(value = "参数枚举编号")
    private String prmEnumId;

    // 参数名称
    @NotBlank(message = "参数名称不能为空")
    @ApiModelProperty(value = "参数名称")
    private String prmName;

    // 参数中文涵义
    @NotBlank(message = "参数中文涵义不能为空")
    @ApiModelProperty(value = "参数中文涵义")
    private String prmCnName;

    // 参数数值
    @NotBlank(message = "参数数值不能为空")
    @ApiModelProperty(value = "参数数值")
    private String prmValue;

    // 参数备注
    @NotBlank(message = "参数备注不能为空")
    @ApiModelProperty(value = "参数备注")
    private String prmRemark;

    // 参数状态，0-正常 1-停用
    @NotBlank(message = "参数状态，0-正常 1-停用不能为空")
    @ApiModelProperty(value = "参数状态，0-正常 1-停用")
    private String prmStatus;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 创建人
    @ApiModelProperty(value = "创建人")
    private String createUser;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 修改人
    @ApiModelProperty(value = "修改人")
    private String updateUser;


}