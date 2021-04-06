package com.drive.admin.pojo.entity;

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
 * 客服回访记录
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_service_return_visit_history")
public class ServiceReturnVisitHistoryEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 回访类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	private String returnVisitType;
	// 回访状态  1：售前回访 2 售后回访
	private String returnVisitStatus;
	// 回访类型明细
	private String returnVisitItem;

	// 是否有意向 0 否 1 是
	private String isIntention;

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