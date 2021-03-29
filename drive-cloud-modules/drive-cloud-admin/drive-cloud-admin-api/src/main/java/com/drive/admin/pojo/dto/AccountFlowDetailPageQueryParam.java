package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 平台账务流水明细
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AccountFlowDetailPageQueryParam extends BasePageQueryParam {


	// 主键
	@ApiModelProperty(value = "主键")
	private String id;
	// 模糊查询字段

	// 账务流水id
	@ApiModelProperty(value = "账务流水id")
	private String accountId;
	// 模糊查询字段

	// 订单号
	@ApiModelProperty(value = "订单号")
	private String orderNo;
	// 模糊查询字段

	// 明细项目类型(	0.考试报名驾校服务费用	1.学车报名驾校服务费用	2.报名平台服务费用	3.课时教练提成费用	4.课时平台提成费用	5.课时推荐学员提成费用	6.课时推荐教练提成费用	7.考试场地服务费用	8.考试车服务费用		9. 平台钱包退款	10.教练钱包退款	11.教练推荐人钱包退款	12.学员推荐人钱包退款	13.场地费退款	14.考试车费用退款		15.取消收入-平台提成费用	16.取消收入-学员提成费用	17.取消收入-教练提成费用	18.取消扣款-教练钱包扣款	19.取消扣款-学员订单扣款)
	@ApiModelProperty(value = "明细项目类型(	0.考试报名驾校服务费用	1.学车报名驾校服务费用	2.报名平台服务费用	3.课时教练提成费用	4.课时平台提成费用	5.课时推荐学员提成费用	6.课时推荐教练提成费用	7.考试场地服务费用	8.考试车服务费用		9. 平台钱包退款	10.教练钱包退款	11.教练推荐人钱包退款	12.学员推荐人钱包退款	13.场地费退款	14.考试车费用退款		15.取消收入-平台提成费用	16.取消收入-学员提成费用	17.取消收入-教练提成费用	18.取消扣款-教练钱包扣款	19.取消扣款-学员订单扣款)")
	private String itemType;
	// 模糊查询字段

	// 明细项目名称
	@ApiModelProperty(value = "明细项目名称")
	private String itemName;
	// 模糊查询字段

	// 订单金额
	@ApiModelProperty(value = "订单金额")
	private BigDecimal itemAmount;
	// 模糊查询字段

	// 是否对账(是，否)
	@ApiModelProperty(value = "是否对账(是，否)")
	private String isReconciled;
	// 模糊查询字段

	// 是否提现(是，否)
	@ApiModelProperty(value = "是否提现(是，否)")
	private String putForward;
	// 模糊查询字段

	// 支付类型(1:支付宝 ;  2 : 微信 ; 3-平台支付宝钱包；4-平台微信钱包；5-司机钱包  345为退款，扣款时使用)
	@ApiModelProperty(value = "支付类型(1:支付宝 ;  2 : 微信 ; 3-平台支付宝钱包；4-平台微信钱包；5-司机钱包  345为退款，扣款时使用)")
	private String payType;
	// 模糊查询字段

	// 收益人类型（1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校）
	@ApiModelProperty(value = "收益人类型（1-学员；2-教练；3-运维；4 平台支付宝；5 平台微信；6 驾校）")
	private String incomeUserType;
	// 模糊查询字段

	// 收益人id
	@ApiModelProperty(value = "收益人id")
	private String incomeUserId;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段

	// 交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)
	@ApiModelProperty(value = "交易类型科目(0101-报名收益，0102-考试收益，0103-课时收益 ; 0201-报名支出,0202-考试支出,0203-课时支出,0204-市场推广支出)")
	private String tradeSubject;
	// 模糊查询字段

	// 交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)
	@ApiModelProperty(value = "交易类型科目明细(010101-报名费提成,010201-考试费提成，010202-考试接送费提成,010301-课时费提成;	020101-驾校提成支出,020201-考试报名费支出,020301-课时教练提成支出,020302-课时驾校提成支出,020401-推荐新用户佣金支出,020402-推荐用户报名推荐费用支出,020403-推荐用户报名课时提成支出)")
	private String tradeSubjectItems;
	// 模糊查询字段
	//private String vagueNameSearch

}