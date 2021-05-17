package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 教练钱包表
 *
 * @author xiaoguo
 */
@Data
public class PlatformWalletVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 钱包用户Id
	@Excel(name = "钱包用户Id", width = 20)
	private String userId;

	@Excel(name = "用户名",width = 20)
	private String userName;

	// 钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)
	@Excel(name = "钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)", width = 20)
	private String walletType;

	// 钱包总额
	@Excel(name = "钱包总额", width = 20)
	private BigDecimal walletAmount;

	// 密码
	@Excel(name = "密码", width = 20)
	private String password;

	// 微信账号
	@Excel(name = "微信账号", width = 20)
	private String wechatAccount;

	// 支付宝账号
	@Excel(name = "支付宝账号", width = 20)
	private String aliAccount;

	// 微信真实姓名
	@Excel(name = "微信真实姓名", width = 20)
	private String wechatRealName;

	// 支付宝账号真实名称
	@Excel(name = "支付宝账号真实名称", width = 20)
	private String aliRealName;

	// 银行卡号
	@Excel(name = "银行卡号", width = 20)
	private String bankAccount;

	// 持卡人姓名
	@Excel(name = "持卡人姓名", width = 20)
	private String bankAccountName;

	// 开户行
	@Excel(name = "开户行", width = 20)
	private String openAccountBank;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}