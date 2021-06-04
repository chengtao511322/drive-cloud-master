package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 学车一费制定价表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("one_fee_system_price")
public class OneFeeSystemPriceEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 名称
	private String name;

	// 驾照类型
	private String driveType;

	// 班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）
	private String classType;

	// 价格
	private BigDecimal price;

	// 科目二教练提成金额
	@TableField(value = "coach_subject_type_2")
	private BigDecimal coachSubjectType2;

	// 科目三教练提成金额
	@TableField(value = "coach_subject_type_3")
	private BigDecimal coachSubjectType3;

	// 科目二驾校提成金额
	@TableField(value = "school_subject_type_2")
	private BigDecimal schoolSubjectType2;

	// 科目三驾校提成金额
	@TableField(value = "school_subject_type_3")
	private BigDecimal schoolSubjectType3;

	// 运营商提成金额
	private BigDecimal operatorChangeMoney;

	// 平台提成金额（所有上级运营商提成金）
	private BigDecimal serviceChangeMoney;

	// 详情介绍
	private String details;

	// 奖金（学员拿证后，教练获得的奖金）
	private BigDecimal bonus;

	// 挂科扣款金额
	private BigDecimal testNotPassWithholdMoney;

	// 是否删除
	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private String isDelete;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 科目一免费考试次数
	@TableField(value = "subject_1_cost_free_number")
	private Integer subject1CostFreeNumber;

	// 科目二免费考试次数
	@TableField(value = "subject_2_cost_free_number")
	private Integer subject2CostFreeNumber;

	// 科目三免费考试次数
	@TableField(value = "subject_3_cost_free_number")
	private Integer subject3CostFreeNumber;

	// 科目四免费考试次数
	@TableField(value = "subject_4_cost_free_number")
	private Integer subject4CostFreeNumber;
	@TableField(value = "coach_subject2_expect_income")
	private BigDecimal coachSubject2ExpectIncome;
	@TableField(value = "coach_subject3_expect_income")
	private BigDecimal coachSubject3ExpectIncome;

	// 价格介绍
	private String priceIntroduce;

	// 学车流程
	private String drivingFlow;

	// 班型等级(用于报名升班控制，只能往大升，不能往小升)
	private Integer classGrade;

	// 原价
	private BigDecimal originalPrice;

	// 是否可升级
	private String isUpgrade;

	// 状态(1-正常，2-停用)
	private String status;

}