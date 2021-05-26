package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;


/**
 * 
 *
 * @author chentao
 */
@Data
public class WalletSettlementDetailEditParam {


    // 主键
    @NotBlank(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private String id;

    // 清算总表id
    @NotBlank(message = "清算总表id不能为空")
    @ApiModelProperty(value = "清算总表id")
    private String settleAccountsId;

    // 钱包明细id
    @NotBlank(message = "钱包明细id不能为空")
    @ApiModelProperty(value = "钱包明细id")
    private String walletDetailId;

    // 清算金额
    @NotBlank(message = "清算金额不能为空")
    @ApiModelProperty(value = "清算金额")
    private BigDecimal settleAccountsMoney;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}