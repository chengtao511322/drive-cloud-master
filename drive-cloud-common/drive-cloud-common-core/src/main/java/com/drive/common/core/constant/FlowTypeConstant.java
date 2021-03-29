package com.drive.common.core.constant;

import java.util.Map;


public enum FlowTypeConstant {
	
	// 报名收益
	ENROLL_INCOME("0101" , TradeSubjectItems.enrollIncome), 
	// 考试收益
	TEST_INCOME("0102",TradeSubjectItems.testIncome),
	// 课时收益
	CLASS_TIME_INCOME("0103",TradeSubjectItems.classTimeIncome),
	// 其他收益
	OTHER_INCOME("0104",TradeSubjectItems.otherIncome),
	// 市场推广收益
	EXTENSION_INCOME("0105",TradeSubjectItems.extensionIncome),
	
	
	// 报名支出
	ENROLL_PAY("0201", TradeSubjectItems.enrollPay),
	// 考试支出
	TEST_PAY("0202", TradeSubjectItems.testPay),
	// 课时支出
	CLASS_TIME_PAY("0203",TradeSubjectItems.classTimePay),
	// 市场推广支出
	EXTENSION_PAY("0204",TradeSubjectItems.extensionPay),
	// 提现支出
	CASH_OUT("0205",TradeSubjectItems.cashOutPay);
	
	
	
	
	private String code;
	
	private  Map<String,Object> itemsMap = null;
	
	private  FlowTypeConstant(String code,Map<String,Object> itemsMap) {
		this.code = code;
		this.itemsMap = itemsMap;
	}

	public String getCode() {
		return code;
	}

	public String getItemsMap(String key) {
		return itemsMap.get(key).toString();
	}
	
	public static void main(String[] args) {
		System.out.println(FlowTypeConstant.EXTENSION_PAY.getCode());
	}
}
