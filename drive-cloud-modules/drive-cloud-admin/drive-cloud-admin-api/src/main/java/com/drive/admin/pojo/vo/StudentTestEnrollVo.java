package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 学员考试报名表
 *
 * @author xiaoguo
 */
@Data
public class StudentTestEnrollVo {


	// 考试报名单号
	@Excel(name = "考试报名单号", width = 20)
	private String testEnrollNo;

	// 学员ID
	@Excel(name = "学员ID", width = 20)
	private String studentId;

	// 实际考试场地-省
	@Excel(name = "实际考试场地-省", width = 20)
	private String provinceId;

	// 实际考试场地-市
	@Excel(name = "实际考试场地-市", width = 20)
	private String cityId;

	// 实际考试场地-区
	@Excel(name = "实际考试场地-区", width = 20)
	private String areaId;

	// 驾照类型（c1；c2...）
	@Excel(name = "驾照类型（c1；c2...）", width = 20)
	private String driveType;

	// 科目类型
	@Excel(name = "科目类型", width = 20)
	private String subjectType;

	// 报名单价
	@Excel(name = "报名单价", width = 20)
	private BigDecimal price;

	// 平台服务费用
	@Excel(name = "平台服务费用", width = 20)
	private BigDecimal serviceCharge;

	// 报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）
	@Excel(name = "报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）", width = 20)
	private String enrollStatus;

	// 希望预约考试时间
	@Excel(name = "希望预约考试时间", width = 20)
	private LocalDateTime testHopeTime;

	// 实际考试时间
	@Excel(name = "实际考试时间", width = 20)
	private LocalDateTime testActualTime;

	// 希望预约考试场地ID
	@Excel(name = "希望预约考试场地ID", width = 20)
	private String testHopeCoachingGridId;
	private String testHopeCoachingGridName;

	// 实际预约考试场地ID
	@Excel(name = "实际预约考试场地ID", width = 20)
	private String testActualCoachingGridId;
	private String testActualCoachingGridName;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 客服ID（系统用户ID）
	@Excel(name = "客服ID（系统用户ID）", width = 20)
	private String userId;

	// 线下客服id(考试接送客服)
	@Excel(name = "线下客服id(考试接送客服)", width = 20)
	private String lineUnderUserId;


	// 线上客服名称
	private String onlineServiceName;
	private String studentName;
	// 线下
	private String lineServiceName;

	// 是否选择接送
	@Excel(name = "是否选择接送", width = 20)
	private String isMeetGive;

	// 接送费用
	@Excel(name = "接送费用", width = 20)
	private BigDecimal meetGicePrice;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班
	@Excel(name = "考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班", width = 20)
	private Integer examType;


	private String provinceName;
	private String cityName;
	private String areaName;
	// 考试次数
	private int examNumber;

}