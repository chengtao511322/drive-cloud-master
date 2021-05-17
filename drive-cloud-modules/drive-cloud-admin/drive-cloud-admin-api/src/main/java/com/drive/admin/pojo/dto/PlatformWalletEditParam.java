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
public class PlatformWalletEditParam {


    // id
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private String id;

    // 钱包用户Id
    @ApiModelProperty(value = "钱包用户Id")
    private String userId;

    // 钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)
    @ApiModelProperty(value = "钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)")
    private String walletType;

    // 钱包总额
    @ApiModelProperty(value = "钱包总额")
    private BigDecimal walletAmount;

    // 密码
    @ApiModelProperty(value = "密码")
    private String password;

    // 微信账号
    @ApiModelProperty(value = "微信账号")
    private String wechatAccount;

    // 支付宝账号
    @ApiModelProperty(value = "支付宝账号")
    private String aliAccount;

    // 微信真实姓名
    @ApiModelProperty(value = "微信真实姓名")
    private String wechatRealName;

    // 支付宝账号真实名称
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
    @ApiModelProperty(value = "运营商id(数据权限标记)")
    private String operatorId;


}