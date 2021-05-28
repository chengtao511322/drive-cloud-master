package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 平台题目选项
 *
 * @author chentao
 */
@Data
public class QuestionOptionsInstallParam {


    @ApiModelProperty(value = "")
    private String id;

    // 题库ID
    @NotNull(message = "题库ID不能为空")
    @ApiModelProperty(value = "题库ID")
    private String questionBankId;

    // 题目选项
    @NotNull(message = "题目选项不能为空")
    @ApiModelProperty(value = "题目选项")
    private String questionOption;

    // 选项内容
    @NotNull(message = "选项内容不能为空")
    @ApiModelProperty(value = "选项内容")
    private String optionContent;

    // 是否正确
    @NotNull(message = "是否正确不能为空")
    @ApiModelProperty(value = "是否正确")
    private String isRight;

    // 创建者
    @ApiModelProperty(value = "创建者")
    private String createUser;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新者
    @ApiModelProperty(value = "更新者")
    private String updateUser;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}