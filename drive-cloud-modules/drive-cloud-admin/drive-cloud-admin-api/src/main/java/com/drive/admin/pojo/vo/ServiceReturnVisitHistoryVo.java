package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 客服回访记录
 *
 * @author xiaoguo
 */
@Data
public class ServiceReturnVisitHistoryVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	@Excel(name = "回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）", width = 20)
	private String returnVisitType;

	// 订单明细单号
	@Excel(name = "订单明细单号", width = 20)
	private String orderDetailNo;

	// 学员id
	@Excel(name = "学员id", width = 20)
	private String studentId;

	// 回访时间
	@Excel(name = "回访时间", width = 20)
	private LocalDateTime returnVisitTime;

	// 回访内容
	@Excel(name = "回访内容", width = 20)
	private String returnVisitContent;

	// 预计下次回访时间
	@Excel(name = "预计下次回访时间", width = 20)
	private LocalDateTime nextReturnVisitTime;

	// 客服id
	private String serviceId;

	@Excel(name = "客服名称", width = 20)
	private String serviceName;

	// 是否完结（是，否）
	@Excel(name = "是否完结（是，否）", width = 20)
	private String isEnd;

	// 科目类型(1-科目一，2-科目三，3-科目三，4-科目四)
	@Excel(name = "科目类型(1-科目一，2-科目三，3-科目三，4-科目四)", width = 20)
	private String subjectType;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}