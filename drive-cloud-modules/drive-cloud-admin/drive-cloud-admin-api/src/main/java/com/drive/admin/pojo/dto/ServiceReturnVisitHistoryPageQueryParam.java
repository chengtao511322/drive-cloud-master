package com.drive.admin.pojo.dto;

import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 客服回访记录
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ServiceReturnVisitHistoryPageQueryParam extends BasePageQueryParam {


	// 主键
	private String id;

	// 回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	private String returnVisitType;

	// 订单明细单号
	private String orderDetailNo;

	// 学员id
	private String studentId;

	// 回访时间
	private LocalDateTime returnVisitTime;

	// 回访内容
	private String returnVisitContent;

	// 预计下次回访时间
	private LocalDateTime nextReturnVisitTime;

	// 客服id
	private String serviceId;

	// 是否完结（是，否）
	private String isEnd;

	// 科目类型(1-科目一，2-科目三，3-科目三，4-科目四)
	private String subjectType;

	// 运营商id(数据权限标记)
	private String operatorId;

}