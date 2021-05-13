package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 一费制学员升班价格控制表
 *
 * @author xiaoguo
 */
@Data
public class OneFeeSystemUpgradeClassPriceInstallParam {


    // id
    @ApiModelProperty(value = "id")
    private String id;

    // 原班类型id
    @ApiModelProperty(value = "原班类型id")
    private String originalClassId;

    // 原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）
    @ApiModelProperty(value = "原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）")
    private String originalClassType;

    // 升级班类型id
    @NotBlank(message = "升级班类型id不能为空")
    @ApiModelProperty(value = "升级班类型id")
    private String upgradeClassId;

    // 升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）
    @ApiModelProperty(value = "升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）")
    private String upgradeClassType;

    // 升班班型价格
    @NotBlank(message = "升班班型价格不能为空")
    @ApiModelProperty(value = "升班班型价格")
    private BigDecimal upgradePrice;

    // 升班价格详情介绍
    @NotBlank(message = "升班价格详情介绍不能为空")
    @ApiModelProperty(value = "升班价格详情介绍")
    private String detailsUrl;

    // 是否删除（0-否，1-是）
    @ApiModelProperty(value = "是否删除（0-否，1-是）")
    private Integer isDelete;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;

    // 运营商提成金额
    @NotBlank(message = "运营商提成金额不能为空")
    @ApiModelProperty(value = "运营商提成金额")
    private BigDecimal operatorChangeMoney;

    // 平台提成金额（所有上级运营商提成金）
    @NotBlank(message = "平台提成金额（所有上级运营商提成金）不能为空")
    @ApiModelProperty(value = "平台提成金额（所有上级运营商提成金）")
    private BigDecimal serviceChangeMoney;

    // 状态(1-正常，2-停用)
    @ApiModelProperty(value = "状态(1-正常，2-停用)")
    private String status;


}