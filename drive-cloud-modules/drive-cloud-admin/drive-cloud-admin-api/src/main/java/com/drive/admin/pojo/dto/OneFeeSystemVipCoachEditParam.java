package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 一费制vip教练
 *
 * @author xiaoguo
 */
@Data
public class OneFeeSystemVipCoachEditParam {


    // 主键
    @NotBlank(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private String id;

    // 教练id
    @NotEmpty(message = "教练id不能为空")
    @ApiModelProperty(value = "教练id")
    private String coachId;

    // 入驻时间
    @ApiModelProperty(value = "入驻时间")
    private LocalDateTime joinTime;

    // 状态（正常，停用）
    @ApiModelProperty(value = "状态（正常，停用）")
    private String stutas;

    // 备注(主要存储，停用原因)
    @ApiModelProperty(value = "备注(主要存储，停用原因)")
    private String remarks;

    // 是否删除
    @ApiModelProperty(value = "是否删除")
    private String isDelete;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 运营商id(数据权限标记)
    @NotEmpty(message = "运营商不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;

    // 预收入单价
    //@NotEmpty(message = "预收入单价不能为空")
    @ApiModelProperty(value = "预收入单价")
    private BigDecimal expectIncomePrice;

    // 教练提成百分比
    //@NotEmpty(message = "教练提成百分比不能为空")
    @ApiModelProperty(value = "教练提成百分比")
    private BigDecimal coachChargePercent;

    // 公车提成百分比（驾校收入）
    //@NotEmpty(message = "公车提成百分比（驾校收入）不能为空")
    @ApiModelProperty(value = "公车提成百分比（驾校收入）")
    private BigDecimal carChargePercent;


}