package com.drive.admin.pojo.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 教练钱包表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_platform_wallet")
public class PlatformWalletEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 钱包用户Id
	private String userId;

	// 钱包类型(1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校；7-运营商)
	private String walletType;

	// 钱包总额
	private BigDecimal walletAmount;

	// 密码
	private String password;

	// 微信账号
	private String wechatAccount;

	// 支付宝账号
	private String aliAccount;

	// 微信真实姓名
	private String wechatRealName;

	// 支付宝账号真实名称
	private String aliRealName;

	// 银行卡号
	private String bankAccount;

	// 持卡人姓名
	private String bankAccountName;

	// 开户行
	private String openAccountBank;

	// 运营商id(数据权限标记)
	private String operatorId;

}