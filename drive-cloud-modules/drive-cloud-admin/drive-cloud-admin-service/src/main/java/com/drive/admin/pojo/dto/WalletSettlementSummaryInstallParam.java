package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 
 *
 * @author chentao
 */
@Data
public class WalletSettlementSummaryInstallParam {


    // 主键
    @NotNull(message = "主键不能为空")
    @ApiModelProperty(value = "主键")
    private String id;

    // 钱包用户id
    @NotNull(message = "钱包用户id不能为空")
    @ApiModelProperty(value = "钱包用户id")
    private String userId;

    // 清算总金额
    @NotNull(message = "清算总金额不能为空")
    @ApiModelProperty(value = "清算总金额")
    private BigDecimal settleAccountsSum;

    // 清算申请时间
    @NotNull(message = "清算申请时间不能为空")
    @ApiModelProperty(value = "清算申请时间")
    private LocalDateTime settleAccountsApplyTime;

    // 清算完成时间
    @NotNull(message = "清算完成时间不能为空")
    @ApiModelProperty(value = "清算完成时间")
    private LocalDateTime settleAccountsCompleteTime;

    // 审核通过时间
    @NotNull(message = "审核通过时间不能为空")
    @ApiModelProperty(value = "审核通过时间")
    private LocalDateTime examineTime;

    // 清算笔数
    @NotNull(message = "清算笔数不能为空")
    @ApiModelProperty(value = "清算笔数")
    private Integer settleAccountsNumber;

    // 清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)
    @NotNull(message = "清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)不能为空")
    @ApiModelProperty(value = "清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)")
    private String status;

    // 清算方式(1-支付宝 ,2-微信,3-银行卡)
    @NotNull(message = "清算方式(1-支付宝 ,2-微信,3-银行卡)不能为空")
    @ApiModelProperty(value = "清算方式(1-支付宝 ,2-微信,3-银行卡)")
    private String settleType;

    // 转账唯一订单号
    @NotNull(message = "转账唯一订单号不能为空")
    @ApiModelProperty(value = "转账唯一订单号")
    private String outBizNo;

    // 备注
    @NotNull(message = "备注不能为空")
    @ApiModelProperty(value = "备注")
    private String remarks;

    // 提现账号
    @NotNull(message = "提现账号不能为空")
    @ApiModelProperty(value = "提现账号")
    private String submitAccount;

    // 提现真实姓名
    @NotNull(message = "提现真实姓名不能为空")
    @ApiModelProperty(value = "提现真实姓名")
    private String submitRealName;

    // 运营商id(数据权限标记)
    @NotNull(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;

    // 驳回时间
    @NotNull(message = "驳回时间不能为空")
    @ApiModelProperty(value = "驳回时间")
    private LocalDateTime rejectTime;

    // 开户行
    @NotNull(message = "开户行不能为空")
    @ApiModelProperty(value = "开户行")
    private String openAccountBank;


}