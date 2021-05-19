package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.admin.util.AdminCacheUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 学员学车预约表
 *
 * @author xiaoguo
 */
@Data
public class 	StudentTrainCarApplyVo {


	// 预约单号
	@Excel(name = "预约单号", width = 20)
	private String trainApplyNo;

	// 训练类型（1-常规练车；2-考试练车）
	@Excel(name = "训练类型（1-常规练车；2-考试练车）", width = 20)
	private String trainType;

	// 教练课时ID
	@Excel(name = "教练课时ID", width = 20)
	private String classId;

	// 学员ID
	@Excel(name = "学员ID", width = 20)
	private String studentId;

	// 驾照类型（c1；c2...）
	@Excel(name = "驾照类型（c1；c2...）", width = 20)
	private String driveType;

	// 科目类型
	@Excel(name = "科目类型", width = 20)
	private String subjectType;

	// 单价
	@Excel(name = "单价", width = 20)
	private BigDecimal price;

	// 总价格
	@Excel(name = "总价格", width = 20)
	private BigDecimal sumPrice;

	// 平台服务费用百分比/H
	@Excel(name = "平台服务费用百分比/H", width = 20)
	private BigDecimal serviceChargePercent;

	// 教练课时费用百分比/H2
	@Excel(name = "教练课时费用百分比/H2", width = 20)
	private BigDecimal coachChargePercent;

	// 平台服务费用
	@Excel(name = "平台服务费用", width = 20)
	private BigDecimal serviceCharge;

	// 教练课时费用
	@Excel(name = "教练课时费用", width = 20)
	private BigDecimal coachCharge;

	// 教练ID
	@Excel(name = "教练ID", width = 20)
	private String coachId;

	// 训练场地ID
	@Excel(name = "训练场地ID", width = 20)
	private String coachingGridId;

	// 接你的时间
	@Excel(name = "接你的时间", width = 20)
	private LocalDateTime takeTime;

	// 上车时间
	@Excel(name = "上车时间", width = 20)
	private LocalDateTime aboardTime;

	// 课程日期
	@Excel(name = "课程日期", width = 20)
	private LocalDate classDate;

	// 开始时间
	@Excel(name = "开始时间", width = 20)
	private LocalDateTime startTime;

	// 结束时间
	@Excel(name = "结束时间", width = 20)
	private LocalDateTime endTime;

	// 学时
	@Excel(name = "学时", width = 20)
	private Integer classHours;

	// 预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）
	@Excel(name = "预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）", width = 20)
	private String applyStatus;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 学员推荐用户类型（1-学员；2-教练；3-运维）
	@Excel(name = "学员推荐用户类型（1-学员；2-教练；3-运维）", width = 20)
	private String studentRecommendUserType;

	// 学员推荐用户ID
	@Excel(name = "学员推荐用户ID", width = 20)
	private String studentRecommendUserId;

	// 学员推荐用户提成百分比
	@Excel(name = "学员推荐用户提成百分比", width = 20)
	private BigDecimal studentRecommendUserChargePercent;

	// 学员推荐用户提成费用
	@Excel(name = "学员推荐用户提成费用", width = 20)
	private BigDecimal studentRecommendUserCharge;

	// 学员常用地址id
	@Excel(name = "学员常用地址id", width = 20)
	private String commonAddressId;

	// 教练推荐用户类型（1-学员；2-教练；3-运营）
	@Excel(name = "教练推荐用户类型（1-学员；2-教练；3-运营）", width = 20)
	private String coachRecommendUserType;

	// 教练推荐用户ID
	@Excel(name = "教练推荐用户ID", width = 20)
	private String coachRecommendUserId;

	// 教练推荐用户提成百分比
	@Excel(name = "教练推荐用户提成百分比", width = 20)
	private BigDecimal coachRecommendUserChargePercent;

	// 教练推荐用户提成费用
	@Excel(name = "教练推荐用户提成费用", width = 20)
	private BigDecimal coachRecommendUserCharge;

	// 取消时间
	@Excel(name = "取消时间", width = 20)
	private LocalDateTime cancelTiem;

	// 取消原因
	@Excel(name = "取消原因", width = 20)
	private String cancelReason;

	// 取消扣款百分比
	@Excel(name = "取消扣款百分比", width = 20)
	private BigDecimal cancelWithholdPercent;

	// 取消扣款金额
	@Excel(name = "取消扣款金额", width = 20)
	private BigDecimal cancelWithholdMoney;

	// 取消退款金额
	@Excel(name = "取消退款金额", width = 20)
	private BigDecimal cancelRefundMoney;

	// 考试训练用车类型（1-教练车；2-考试车）
	@Excel(name = "考试训练用车类型（1-教练车；2-考试车）", width = 20)
	private String carType;

	// 是否删除（0-否;1-是）
	@Excel(name = "是否删除（0-否;1-是）", width = 20)
	private String isDelete;

	// 实际开始时间
	@Excel(name = "实际开始时间", width = 20)
	private LocalDateTime actualStartTime;

	// 实际结束时间
	@Excel(name = "实际结束时间", width = 20)
	private LocalDateTime actualEndTime;

	// 课时点评
	@Excel(name = "课时点评", width = 20)
	private String classAppraise;

	// 公车提成费用
	@Excel(name = "公车提成费用", width = 20)
	private BigDecimal publicCarPrice;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	//课程名称
	@Excel(name = "教练课程名称",width = 20)
	private String className;

	//教练名称
	@Excel(name = "教练名称",width = 20)
	private String coachName;

	//学员名称
	@Excel(name = "学员名称",width = 20)
	private String studentName;

	//训练场地名称
	@Excel(name = "训练场地名称",width = 20)
	private String coachingGridName;

	private int classHourTotal;


	public void setCoachId(String coachId) {
		this.coachId = coachId;
		this.coachName = AdminCacheUtil.getCoachName(coachId);
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public void setCoachingGridId(String coachingGridId) {
		this.coachingGridId = coachingGridId;
		this.coachingGridName = AdminCacheUtil.getCoachingGridName(coachingGridId);
	}
}