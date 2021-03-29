package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 学员考试报名表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_student_test_enroll")
public class StudentTestEnrollEntity extends BaseEntity {


	// 考试报名单号
	@TableId(type= IdType.ID_WORKER)
	@TableField(value="test_enroll_no")
	private String testEnrollNo;

	// 学员ID
	private String studentId;

	// 实际考试场地-省
	private String provinceId;

	// 实际考试场地-市
	private String cityId;

	// 实际考试场地-区
	private String areaId;

	// 驾照类型（c1；c2...）
	private String driveType;

	// 科目类型
	private String subjectType;

	// 报名单价
	private BigDecimal price;

	// 平台服务费用
	private BigDecimal serviceCharge;

	// 报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）
	private String enrollStatus;

	// 希望预约考试时间
	private LocalDateTime testHopeTime;

	// 实际考试时间
	private LocalDateTime testActualTime;

	// 希望预约考试场地ID
	private String testHopeCoachingGridId;

	// 实际预约考试场地ID
	private String testActualCoachingGridId;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 客服ID（系统用户ID）
	private String userId;

	// 线下客服id(考试接送客服)
	private String lineUnderUserId;

	// 是否选择接送
	private String isMeetGive;

	// 接送费用
	private BigDecimal meetGicePrice;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班
	private Integer examType;

}