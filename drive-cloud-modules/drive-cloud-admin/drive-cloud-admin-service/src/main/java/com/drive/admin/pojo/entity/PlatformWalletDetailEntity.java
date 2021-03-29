package com.drive.admin.pojo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;


import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 教练钱包表明细
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_platform_wallet_detail")
public class PlatformWalletDetailEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 钱包用户Id
	private String userId;

	// 账务流水明细id/提现时为清算记录id
	private String accountDetailId;

	// 交易金额
	private BigDecimal tradeAmount;

	// 交易类型（1-收益、2-支出）
	private String tradeType;

	// 收入/支出名称
	private String walletDetailName;

	// 交易状态(0-失败,1-成功)
	private String status;

	// 交易时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 余额(进账，处长之前的余额)
	private BigDecimal balance;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)
	private String tradeSubject;

	// 交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)
	private String tradeSubjectItems;

	// 数据创建时间
	private LocalDateTime setUpDate;

}