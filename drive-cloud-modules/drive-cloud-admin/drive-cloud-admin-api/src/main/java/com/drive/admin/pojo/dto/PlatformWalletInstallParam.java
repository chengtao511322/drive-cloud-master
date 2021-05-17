package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


/**
 * 教练钱包表
 *
 * @author xiaoguo
 */
@Data
public class PlatformWalletInstallParam {



    @ApiModelProperty(value = "id")
    private String id;

    // 钱包用户Id
    @NotBlank(message = "钱包用户Id不能为空")
    @ApiModelProperty(value = "钱包用户Id")
    private String userId;

    // 钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)
    @NotBlank(message = "钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)不能为空")
    @ApiModelProperty(value = "钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)")
    private String walletType;


    @ApiModelProperty(value = "钱包总额")
    private BigDecimal walletAmount;

    // 密码
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    // 微信账号
    @NotBlank(message = "微信账号不能为空")
    @ApiModelProperty(value = "微信账号")
    private String wechatAccount;

    // 支付宝账号
    @NotBlank(message = "支付宝账号不能为空")
    @ApiModelProperty(value = "支付宝账号")
    private String aliAccount;

    // 微信真实姓名
    @NotBlank(message = "微信真实姓名不能为空")
    @ApiModelProperty(value = "微信真实姓名")
    private String wechatRealName;

    // 支付宝账号真实名称
    @NotBlank(message = "支付宝账号真实名称不能为空")
    @ApiModelProperty(value = "支付宝账号真实名称")
    private String aliRealName;

    // 银行卡号

    @ApiModelProperty(value = "银行卡号")
    private String bankAccount;

    // 持卡人姓名
    @ApiModelProperty(value = "持卡人姓名")
    private String bankAccountName;

    // 开户行
    @ApiModelProperty(value = "开户行")
    private String openAccountBank;

    // 运营商id(数据权限标记)
    @NotBlank(message = "运营商id(数据权限标记)不能为空")
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}