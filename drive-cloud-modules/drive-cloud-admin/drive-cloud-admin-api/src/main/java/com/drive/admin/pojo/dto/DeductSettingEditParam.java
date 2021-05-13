package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


/**
 * 提成设置表
 *
 * @author xiaoguo
 */
@Data
public class DeductSettingEditParam {


    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String name;

    // 用户类型 1-个人，2-商铺,3 企业类型 4  学校类型
    @NotBlank(message = "用户类型 1-个人，2-商铺,3 企业类型 4  学校类型不能为空")
    @ApiModelProperty(value = "用户类型 1-个人，2-商铺,3 企业类型 4  学校类型")
    private Integer userType;

    // 经理课时提成比例
    @NotBlank(message = "经理课时提成比例不能为空")
    @ApiModelProperty(value = "经理课时提成比例")
    private BigDecimal managerHourProportion;

    // 经理报名提成金额
    @NotBlank(message = "经理报名提成金额不能为空")
    @ApiModelProperty(value = "经理报名提成金额")
    private BigDecimal managerApplyAmount;

    // 代理商课时提成比
    @NotBlank(message = "代理商课时提成比不能为空")
    @ApiModelProperty(value = "代理商课时提成比")
    private BigDecimal agencyHourProportion;

    // 代理商报名提成金额
    @NotBlank(message = "代理商报名提成金额不能为空")
    @ApiModelProperty(value = "代理商报名提成金额")
    private BigDecimal agencyApplyAmount;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;

    // 渠道经理ID
    @NotBlank(message = "渠道经理ID不能为空")
    @ApiModelProperty(value = "渠道经理ID")
    private String recommendManagerId;

    // 代理商ID
    @NotBlank(message = "代理商ID不能为空")
    @ApiModelProperty(value = "代理商ID")
    private String recommendAgencyId;


}