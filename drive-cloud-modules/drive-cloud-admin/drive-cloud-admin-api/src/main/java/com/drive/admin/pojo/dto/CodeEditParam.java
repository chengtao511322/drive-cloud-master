package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 字典表
 *
 * @author chentao
 */
@Data
public class CodeEditParam {


    // 主键
    @NotBlank(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private String codeId;

    // 分类
    @NotBlank(message = "分类不能为空")
    @ApiModelProperty(value = "分类")
    private String category;

    // 值
    @NotBlank(message = "值不能为空")
    @ApiModelProperty(value = "值")
    private String codeValue;

    // 显示项
    @NotBlank(message = "显示项不能为空")
    @ApiModelProperty(value = "显示项")
    private String disText;

    // 标签
    @NotBlank(message = "标签不能为空")
    @ApiModelProperty(value = "标签")
    private String tags;

    // 排序
    @NotBlank(message = "排序不能为空")
    @ApiModelProperty(value = "排序")
    private String odBy;

    // 备注
    @NotBlank(message = "备注不能为空")
    @ApiModelProperty(value = "备注")
    private String remarks;

    // 父项
    @NotBlank(message = "父项不能为空")
    @ApiModelProperty(value = "父项")
    private String supCodeId;

    // 创建人
    @NotBlank(message = "创建人不能为空")
    @ApiModelProperty(value = "创建人")
    private String createUser;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新人
    @NotBlank(message = "更新人不能为空")
    @ApiModelProperty(value = "更新人")
    private String updateUser;

    // 更新时间
    @NotBlank(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}