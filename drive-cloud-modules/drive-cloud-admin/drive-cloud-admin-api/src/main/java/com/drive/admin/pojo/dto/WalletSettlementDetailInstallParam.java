package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 
 *
 * @author chentao
 */
@Data
public class WalletSettlementDetailInstallParam {


    // 主键
    @NotNull(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private String id;

    // 清算总表id
    @NotNull(message = "清算总表id不能为空")
    @ApiModelProperty(value = "清算总表id")
    private String settleAccountsId;

    // 钱包明细id
    @NotNull(message = "钱包明细id不能为空")
    @ApiModelProperty(value = "钱包明细id")
    private String walletDetailId;

    // 清算金额
    @NotNull(message = "清算金额不能为空")
    @ApiModelProperty(value = "清算金额")
    private BigDecimal settleAccountsMoney;

    // 运营商id(数据权限标记)
    @NotNull(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}