package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 学员学车预约表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class StudentTrainCarApplyPageQueryParam extends BasePageQueryParam {


	// 预约单号
	@ApiModelProperty(value = "预约单号")
	private String trainApplyNo;
	// 模糊查询字段

	// 训练类型（1-常规练车；2-考试练车）
	@ApiModelProperty(value = "训练类型（1-常规练车；2-考试练车）")
	private String trainType;
	// 模糊查询字段

	// 教练课时ID
	@ApiModelProperty(value = "教练课时ID")
	private String classId;
	// 模糊查询字段

	// 学员ID
	@ApiModelProperty(value = "学员ID")
	private String studentId;
	// 模糊查询字段

	// 驾照类型（c1；c2...）
	@ApiModelProperty(value = "驾照类型（c1；c2...）")
	private String driveType;
	// 模糊查询字段

	// 科目类型
	@ApiModelProperty(value = "科目类型")
	private String subjectType;
	// 模糊查询字段

	// 单价
	@ApiModelProperty(value = "单价")
	private BigDecimal price;
	// 模糊查询字段

	// 总价格
	@ApiModelProperty(value = "总价格")
	private BigDecimal sumPrice;
	// 模糊查询字段

	// 平台服务费用百分比/H
	@ApiModelProperty(value = "平台服务费用百分比/H")
	private BigDecimal serviceChargePercent;
	// 模糊查询字段

	// 教练课时费用百分比/H2
	@ApiModelProperty(value = "教练课时费用百分比/H2")
	private BigDecimal coachChargePercent;
	// 模糊查询字段

	// 平台服务费用
	@ApiModelProperty(value = "平台服务费用")
	private BigDecimal serviceCharge;
	// 模糊查询字段

	// 教练课时费用
	@ApiModelProperty(value = "教练课时费用")
	private BigDecimal coachCharge;
	// 模糊查询字段

	// 教练ID
	@ApiModelProperty(value = "教练ID")
	private String coachId;
	// 模糊查询字段

	// 训练场地ID
	@ApiModelProperty(value = "训练场地ID")
	private String coachingGridId;
	// 模糊查询字段

	// 接你的时间
	@ApiModelProperty(value = "接你的时间")
	private LocalDateTime takeTime;
	// 模糊查询字段

	// 上车时间
	@ApiModelProperty(value = "上车时间")
	private LocalDateTime aboardTime;
	// 模糊查询字段

	// 课程日期
	@ApiModelProperty(value = "课程日期")
	private LocalDate classDate;
	// 模糊查询字段

	// 开始时间
	@ApiModelProperty(value = "开始时间")
	private LocalDateTime startTime;
	// 模糊查询字段

	// 结束时间
	@ApiModelProperty(value = "结束时间")
	private String endTime;
	// 模糊查询字段

	// 学时
	@ApiModelProperty(value = "学时")
	private Integer classHours;
	// 模糊查询字段

	// 预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）
	@ApiModelProperty(value = "预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）")
	private String applyStatus;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 学员推荐用户类型（1-学员；2-教练；3-运维）
	@ApiModelProperty(value = "学员推荐用户类型（1-学员；2-教练；3-运维）")
	private String studentRecommendUserType;
	// 模糊查询字段

	// 学员推荐用户ID
	@ApiModelProperty(value = "学员推荐用户ID")
	private String studentRecommendUserId;
	// 模糊查询字段

	// 学员推荐用户提成百分比
	@ApiModelProperty(value = "学员推荐用户提成百分比")
	private BigDecimal studentRecommendUserChargePercent;
	// 模糊查询字段

	// 学员推荐用户提成费用
	@ApiModelProperty(value = "学员推荐用户提成费用")
	private BigDecimal studentRecommendUserCharge;
	// 模糊查询字段

	// 学员常用地址id
	@ApiModelProperty(value = "学员常用地址id")
	private String commonAddressId;
	// 模糊查询字段

	// 教练推荐用户类型（1-学员；2-教练；3-运营）
	@ApiModelProperty(value = "教练推荐用户类型（1-学员；2-教练；3-运营）")
	private String coachRecommendUserType;
	// 模糊查询字段

	// 教练推荐用户ID
	@ApiModelProperty(value = "教练推荐用户ID")
	private String coachRecommendUserId;
	// 模糊查询字段

	// 教练推荐用户提成百分比
	@ApiModelProperty(value = "教练推荐用户提成百分比")
	private BigDecimal coachRecommendUserChargePercent;
	// 模糊查询字段

	// 教练推荐用户提成费用
	@ApiModelProperty(value = "教练推荐用户提成费用")
	private BigDecimal coachRecommendUserCharge;
	// 模糊查询字段

	// 取消时间
	@ApiModelProperty(value = "取消时间")
	private LocalDateTime cancelTiem;
	// 模糊查询字段

	// 取消原因
	@ApiModelProperty(value = "取消原因")
	private String cancelReason;
	// 模糊查询字段

	// 取消扣款百分比
	@ApiModelProperty(value = "取消扣款百分比")
	private BigDecimal cancelWithholdPercent;
	// 模糊查询字段

	// 取消扣款金额
	@ApiModelProperty(value = "取消扣款金额")
	private BigDecimal cancelWithholdMoney;
	// 模糊查询字段

	// 取消退款金额
	@ApiModelProperty(value = "取消退款金额")
	private BigDecimal cancelRefundMoney;
	// 模糊查询字段

	// 考试训练用车类型（1-教练车；2-考试车）
	@ApiModelProperty(value = "考试训练用车类型（1-教练车；2-考试车）")
	private String carType;
	// 模糊查询字段

	// 是否删除（0-否;1-是）
	@ApiModelProperty(value = "是否删除（0-否;1-是）")
	private String isDelete;
	// 模糊查询字段

	// 实际开始时间
	@ApiModelProperty(value = "实际开始时间")
	private LocalDateTime actualStartTime;
	// 模糊查询字段

	// 实际结束时间
	@ApiModelProperty(value = "实际结束时间")
	private LocalDateTime actualEndTime;
	// 模糊查询字段

	// 课时点评
	@ApiModelProperty(value = "课时点评")
	private String classAppraise;
	// 模糊查询字段

	// 公车提成费用
	@ApiModelProperty(value = "公车提成费用")
	private BigDecimal publicCarPrice;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段
	//private String vagueNameSearch

}