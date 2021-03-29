package com.drive.common.core.constant;

import java.util.HashMap;
import java.util.Map;

public class TradeSubjectItems {

	//报名收入明细
	public static Map<String,Object> enrollIncome = new HashMap<String, Object>();
	//考试收入明细
	public static Map<String,Object> testIncome = new HashMap<String, Object>();
	//课时收入明细
	public static Map<String,Object> classTimeIncome = new HashMap<String, Object>();
	//其他收入明细
	public static Map<String,Object> otherIncome = new HashMap<String, Object>();
	//市场推广收入明细
	public static Map<String,Object> extensionIncome = new HashMap<String, Object>();
	
	
	
	//报名支出明细
	public static Map<String,Object> enrollPay = new HashMap<String, Object>();
	//考试支出明细
	public static Map<String,Object> testPay = new HashMap<String, Object>();
	//课时支出明细
	public static Map<String,Object> classTimePay = new HashMap<String, Object>();
	//市场推广支出明细
	public static Map<String,Object> extensionPay = new HashMap<String, Object>();
	//市场推广支出明细
	public static Map<String,Object> cashOutPay = new HashMap<String, Object>();
	
	
	
	
	static {
		
		//报名收入明细
		enrollIncome.put("ENROLL_SHARE", "010101"); // 报名提成
		//考试收入明细
		testIncome.put("TEST_SHARE", "010201"); // 考试费提成
		testIncome.put("TEST_TRANSFER_SHARE", "010202"); // 考试接送费提成
		//课时收入明细
		classTimeIncome.put("CLASS_TIME_SHARE", "010301"); // 课时费提成
		classTimeIncome.put("CLASS_COACHING_SHARE", "010302"); // 考试场地费提成
		classTimeIncome.put("CLASS_TEST_CAR_SHARE", "010303"); // 考试车费用提成
		classTimeIncome.put("CLASS_CANCEL_SHARE", "010304"); // 课时取消订单提成
		classTimeIncome.put("CLASS_MERITS_SHARE", "010305"); // 课时绩效提成
		classTimeIncome.put("CLASS_BONUS", "010306"); // VIP包过奖金提成
		classTimeIncome.put("CLASS_WITHHOLD_BONUS", "010307"); // VIP包过奖金扣款提成
		
		//其他收入明细
		otherIncome.put("CASH_OUT_FAIL_BACK", "010401"); //提现失败退还
		//市场推广收入明细
		extensionIncome.put("EXTENSION_NEW_USER_INCOME", "010501"); //推荐新用户收入
		extensionIncome.put("WRITE_INVITE_CODE_INCOME", "010502"); //填写邀请码收入
		extensionIncome.put("EXTENSION_NEW_USER_ENROLL_INCOME", "010503"); // 推荐新用户报名佣金收益
		extensionIncome.put("EXTENSION_NEW_USER_ENROLL_CLASS_INCOME", "010504"); // 推荐用户报名课时提成收入
		extensionIncome.put("EXTENSION_NEW_SELL_CLASS_COUPON", "010505"); // 课时大礼包收入
		extensionIncome.put("EXTENSION_NEW_COUPON_SUBSIDY", "010506"); // 优惠卷补贴收入
		
		
		
		
		//报名支出明细
		enrollPay.put("SCHOOL_PAY", "020101"); // 驾校提成支出
		//考试支出明细
		testPay.put("TEST_ENROLL_PAY", "020201"); // 考试报名费支出
		//课时支出明细
		classTimePay.put("CLASS_COACH_SHARE_PAY", "020301"); // 课时教练提成支出
		classTimePay.put("CLASS_SCHOOL_SHARE_PAY", "020302"); // 课时驾校提成支出
		classTimePay.put("CLASS_CANCEL_PAY", "020303"); // 课时取消退款
		classTimePay.put("CLASS_CANCEL_DEDUCTION_PAY", "020304"); // 课时取消扣款
		classTimePay.put("CLASS_MERITS_PAY", "020305"); // 课时绩效支出
		
		//市场推广支出明细
		extensionPay.put("RECOMMEND_NEW_USER_PAY", "020401"); // 推荐新用户提交支出
		extensionPay.put("RECOMMEND_NEW_USER_ENROLL_PAY", "020402"); // 推荐用户报名推荐费用支出
		extensionPay.put("RECOMMEND_NEW_USER_CLASS_SHARE_PAY", "020403"); // 推荐用户报名课时提成支出
		extensionPay.put("COUPON_PAY", "020404");  //优惠卷支出
		extensionPay.put("FIRST_SEND_INVITATION_PAY", "020405"); // 论坛首次发帖奖励支出
		extensionPay.put("EXTENSION_NEW_COUPON_SUBSIDY_PAY", "020406"); // 优惠卷补贴支出
		
		// 提现
		cashOutPay.put("CASH_OUT_PAY", "020501"); // 提现支出
	}
	
}
