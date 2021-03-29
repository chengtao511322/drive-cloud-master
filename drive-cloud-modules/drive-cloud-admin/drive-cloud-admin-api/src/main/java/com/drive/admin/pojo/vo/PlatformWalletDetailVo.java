package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 教练钱包表明细
 *
 * @author xiaoguo
 */
@Data
public class PlatformWalletDetailVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 钱包用户Id
	@Excel(name = "钱包用户Id", width = 20)
	private String userId;

	// 账务流水明细id/提现时为清算记录id
	@Excel(name = "账务流水明细id/提现时为清算记录id", width = 20)
	private String accountDetailId;

	// 交易金额
	@Excel(name = "交易金额", width = 20)
	private BigDecimal tradeAmount;

	// 交易类型（1-收益、2-支出）
	@Excel(name = "交易类型（1-收益、2-支出）", width = 20)
	private String tradeType;

	// 收入/支出名称
	@Excel(name = "收入/支出名称", width = 20)
	private String walletDetailName;

	// 交易状态(0-失败,1-成功)
	@Excel(name = "交易状态(0-失败,1-成功)", width = 20)
	private String status;

	// 交易时间
	@Excel(name = "交易时间", width = 20)
	private LocalDateTime createTime;

	// 余额(进账，处长之前的余额)
	@Excel(name = "余额(进账，处长之前的余额)", width = 20)
	private BigDecimal balance;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)
	@Excel(name = "交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)", width = 20)
	private String tradeSubject;

	// 交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)
	@Excel(name = "交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)", width = 20)
	private String tradeSubjectItems;

	// 数据创建时间
	@Excel(name = "数据创建时间", width = 20)
	private LocalDateTime setUpDate;

}