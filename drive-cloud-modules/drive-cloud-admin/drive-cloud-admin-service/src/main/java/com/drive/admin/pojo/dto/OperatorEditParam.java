package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 运营商基础信息
 *
 * @author xiaoguo
 */
@Data
public class OperatorEditParam {


    // 主键
    @NotBlank(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private String id;

    // 运营商名称
    @NotBlank(message = "运营商名称不能为空")
    @ApiModelProperty(value = "运营商名称")
    private String name;

    // 运营商级别(1-省，2-市，3-区(县))
    @NotBlank(message = "运营商级别(1-省，2-市，3-区(县))不能为空")
    @ApiModelProperty(value = "运营商级别(1-省，2-市，3-区(县))")
    private Integer level;

    // 平台提成百分比
    @NotBlank(message = "平台提成百分比不能为空")
    @ApiModelProperty(value = "平台提成百分比")
    private BigDecimal platformChargePercent;

    // 是否删除
    @NotBlank(message = "是否删除不能为空")
    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    // 创建时间
    @NotBlank(message = "创建时间不能为空")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @NotBlank(message = "更新时间不能为空")
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 使用状态（1：正常；2：停用）
    @NotBlank(message = "使用状态（1：正常；2：停用）不能为空")
    @ApiModelProperty(value = "使用状态（1：正常；2：停用）")
    private String status;

    // 父级代理商id
    @NotBlank(message = "父级代理商id不能为空")
    @ApiModelProperty(value = "父级代理商id")
    private String parentId;

    // 分配课时绩效比例
    @NotBlank(message = "分配课时绩效比例不能为空")
    @ApiModelProperty(value = "分配课时绩效比例")
    private BigDecimal hourPerformanceRatio;

    // 其他benaid
    @NotBlank(message = "其他benaid不能为空")
    @ApiModelProperty(value = "其他benaid")
    private String channelBeanId;


}