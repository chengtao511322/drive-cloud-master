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
 * 
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_wallet_settlement_detail")
public class WalletSettlementDetailEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 清算总表id
	private String settleAccountsId;

	// 钱包明细id
	private String walletDetailId;

	// 清算金额
	private BigDecimal settleAccountsMoney;

	// 运营商id(数据权限标记)
	private String operatorId;

}