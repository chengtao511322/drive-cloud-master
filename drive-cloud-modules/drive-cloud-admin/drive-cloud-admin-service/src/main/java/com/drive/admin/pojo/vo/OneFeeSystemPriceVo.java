package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 学车一费制定价表
 *
 * @author xiaoguo
 */
@Data
public class OneFeeSystemPriceVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 名称
	@Excel(name = "名称", width = 20)
	private String name;

	// 驾照类型
	@Excel(name = "驾照类型", width = 20)
	private String driveType;

	// 班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）
	@Excel(name = "班型（4-vip包过，3-vip普通，2-vip特训，1-自主模式）", width = 20)
	private String classType;

	// 价格
	@Excel(name = "价格", width = 20)
	private BigDecimal price;

	// 科目二教练提成金额
	@Excel(name = "科目二教练提成金额", width = 20)
	private BigDecimal coachSubjectType2;

	// 科目三教练提成金额
	@Excel(name = "科目三教练提成金额", width = 20)
	private BigDecimal coachSubjectType3;

	// 科目二驾校提成金额
	@Excel(name = "科目二驾校提成金额", width = 20)
	private BigDecimal schoolSubjectType2;

	// 科目三驾校提成金额
	@Excel(name = "科目三驾校提成金额", width = 20)
	private BigDecimal schoolSubjectType3;

	// 运营商提成金额
	@Excel(name = "运营商提成金额", width = 20)
	private BigDecimal operatorChangeMoney;

	// 平台提成金额（所有上级运营商提成金）
	@Excel(name = "平台提成金额（所有上级运营商提成金）", width = 20)
	private BigDecimal serviceChangeMoney;

	// 详情介绍
	@Excel(name = "详情介绍", width = 20)
	private String details;

	// 奖金（学员拿证后，教练获得的奖金）
	@Excel(name = "奖金（学员拿证后，教练获得的奖金）", width = 20)
	private BigDecimal bonus;

	// 挂科扣款金额
	@Excel(name = "挂科扣款金额", width = 20)
	private BigDecimal testNotPassWithholdMoney;

	// 是否删除
	@Excel(name = "是否删除", width = 20)
	private String isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 科目一免费考试次数
	@Excel(name = "科目一免费考试次数", width = 20)
	private Integer subject1CostFreeNumber;

	// 科目二免费考试次数
	@Excel(name = "科目二免费考试次数", width = 20)
	private Integer subject2CostFreeNumber;

	// 科目三免费考试次数
	@Excel(name = "科目三免费考试次数", width = 20)
	private Integer subject3CostFreeNumber;

	// 科目四免费考试次数
	@Excel(name = "科目四免费考试次数", width = 20)
	private Integer subject4CostFreeNumber;

	// 价格介绍
	@Excel(name = "价格介绍", width = 20)
	private String priceIntroduce;

	// 学车流程
	@Excel(name = "学车流程", width = 20)
	private String drivingFlow;

	// 班型等级(用于报名升班控制，只能往大升，不能往小升)
	@Excel(name = "班型等级(用于报名升班控制，只能往大升，不能往小升)", width = 20)
	private Integer classGrade;

	// 原价
	@Excel(name = "原价", width = 20)
	private BigDecimal originalPrice;

	// 是否可升级
	@Excel(name = "是否可升级", width = 20)
	private String isUpgrade;

	// 状态(1-正常，2-停用)
	@Excel(name = "状态(1-正常，2-停用)", width = 20)
	private String status;

	private List<ServicePackageDetailVo> servicePackageDetailVos;

	private BigDecimal coachSubject2ExpectIncome;
	private BigDecimal coachSubject3ExpectIncome;

}