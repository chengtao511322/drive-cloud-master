package com.drive.admin.pojo.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDate;
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
 * 学员学车预约表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_student_train_car_apply")
public class StudentTrainCarApplyEntity extends BaseEntity {


	// 预约单号
	@TableId(type= IdType.ID_WORKER)
	private String trainApplyNo;

	// 训练类型（1-常规练车；2-考试练车）
	private String trainType;

	// 教练课时ID
	private String classId;

	// 学员ID
	private String studentId;

	// 驾照类型（c1；c2...）
	private String driveType;

	// 科目类型
	private String subjectType;

	// 单价
	private BigDecimal price;

	// 总价格
	private BigDecimal sumPrice;

	// 平台服务费用百分比/H
	private BigDecimal serviceChargePercent;

	// 教练课时费用百分比/H2
	private BigDecimal coachChargePercent;

	// 平台服务费用
	private BigDecimal serviceCharge;

	// 教练课时费用
	private BigDecimal coachCharge;

	// 教练ID
	private String coachId;

	// 训练场地ID
	private String coachingGridId;

	// 接你的时间
	private LocalDateTime takeTime;

	// 上车时间
	private LocalDateTime aboardTime;

	// 课程日期
	private LocalDate classDate;

	// 开始时间
	private LocalDateTime startTime;

	// 结束时间
	private LocalDateTime endTime;

	// 学时
	private Integer classHours;

	// 预约状态（0-待支付，1-预约成功；2-预约失败；3-预约取消；4-练车中；5-练车完成 6 接你中 7 已上车）
	private String applyStatus;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 学员推荐用户类型（1-学员；2-教练；3-运维）
	private String studentRecommendUserType;

	// 学员推荐用户ID
	private String studentRecommendUserId;

	// 学员推荐用户提成百分比
	private BigDecimal studentRecommendUserChargePercent;

	// 学员推荐用户提成费用
	private BigDecimal studentRecommendUserCharge;

	// 学员常用地址id
	private String commonAddressId;

	// 教练推荐用户类型（1-学员；2-教练；3-运营）
	private String coachRecommendUserType;

	// 教练推荐用户ID
	private String coachRecommendUserId;

	// 教练推荐用户提成百分比
	private BigDecimal coachRecommendUserChargePercent;

	// 教练推荐用户提成费用
	private BigDecimal coachRecommendUserCharge;

	// 取消时间
	private LocalDateTime cancelTiem;

	// 取消原因
	private String cancelReason;

	// 取消扣款百分比
	private BigDecimal cancelWithholdPercent;

	// 取消扣款金额
	private BigDecimal cancelWithholdMoney;

	// 取消退款金额
	private BigDecimal cancelRefundMoney;

	// 考试训练用车类型（1-教练车；2-考试车）
	private String carType;

	// 是否删除（0-否;1-是）
	@TableLogic
   	@TableField(value="is_delete")
	private String isDelete;

	// 实际开始时间
	private LocalDateTime actualStartTime;

	// 实际结束时间
	private LocalDateTime actualEndTime;

	// 课时点评
	private String classAppraise;

	// 公车提成费用
	private BigDecimal publicCarPrice;

	// 运营商id(数据权限标记)
	private String operatorId;

}