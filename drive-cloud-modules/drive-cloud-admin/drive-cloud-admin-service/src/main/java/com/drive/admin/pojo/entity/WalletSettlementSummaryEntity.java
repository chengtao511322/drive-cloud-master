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
import java.time.LocalDateTime;
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
@TableName("t_wallet_settlement_summary")
public class WalletSettlementSummaryEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 钱包用户id
	private String userId;

	// 清算总金额
	private BigDecimal settleAccountsSum;

	// 清算申请时间
	private LocalDateTime settleAccountsApplyTime;

	// 清算完成时间
	private LocalDateTime settleAccountsCompleteTime;

	// 审核通过时间
	private LocalDateTime examineTime;

	// 清算笔数
	private Integer settleAccountsNumber;

	// 清算状态(0.待审核,1.待清算，2-清算成功，3.清算失败,4.提现异常)
	private String status;

	// 清算方式(1-支付宝 ,2-微信,3-银行卡)
	private String settleType;

	// 转账唯一订单号
	private String outBizNo;

	// 备注
	private String remarks;

	// 提现账号
	private String submitAccount;

	// 提现真实姓名
	private String submitRealName;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 驳回时间
	private LocalDateTime rejectTime;

	// 开户行
	private String openAccountBank;

}