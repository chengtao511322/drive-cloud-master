package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 运营商基础信息
 *
 * @author xiaoguo
 */
@Data
public class OperatorEditParam {


    // 主键
    @ApiModelProperty(value = "主键")
    private String id;

    // 运营商名称
    @ApiModelProperty(value = "运营商名称")
    private String name;

    // 运营商级别(1-省，2-市，3-区(县))
    @ApiModelProperty(value = "运营商级别(1-省，2-市，3-区(县))")
    private Integer level;

    // 平台提成百分比
    @ApiModelProperty(value = "平台提成百分比")
    private BigDecimal platformChargePercent;

    // 是否删除
    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 使用状态（1：正常；2：停用）
    @ApiModelProperty(value = "使用状态（1：正常；2：停用）")
    private String status;

    // 父级代理商id
    @ApiModelProperty(value = "父级代理商id")
    private String parentId;

    // 分配课时绩效比例
    @ApiModelProperty(value = "分配课时绩效比例")
    private BigDecimal hourPerformanceRatio;

    // 其他benaid
    @ApiModelProperty(value = "其他benaid")
    private String channelBeanId;
    // 运营区域
    private List<OperatorAreaInstallParam> operatorAreaInstallParams;

}