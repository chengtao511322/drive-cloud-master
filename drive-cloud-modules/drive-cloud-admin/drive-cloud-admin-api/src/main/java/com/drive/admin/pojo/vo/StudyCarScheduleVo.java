package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.utils.CoachUtil;
import com.drive.common.core.utils.StringUtils;
import lombok.Data;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台账务流水明细
 *
 * @author xiaoguo
 */
@Data
public class StudyCarScheduleVo {


	//用户姓名
	@Excel(name = "用户姓名", width = 20)
	private String realName;

	//电话号码
	@Excel(name = "电话号码", width = 20)
	private String telephone;

	//驾照类型
	@Excel(name = "驾照类型", width = 20)
	private String driveType;


	//驾照类型
	@Excel(name = "报名线上客服", width = 20)
	private String userId;

	//驾照类型
	@Excel(name = "报名线下客服", width = 20)
	private String lineUnderUserId;

	// 报名状态
	@Excel(name = "学车报名单状态", width = 20)
	private String enrollStatus;

	// 科目一考试状态
	@Excel(name = "科目一考试状态", width = 20)
	private String subjectType1TestEnrollStatus;

	// 科二常规已约课时数
	@Excel(name = "科二常规已约课时数", width = 20)
	private int subjectType2TrainAppointmentSumTime;

	// 科二常规已约课时数
	@Excel(name = "科二常规完成课时数", width = 20)
	private int subjectType2TrainCompleteSumTime;

	// 科二考试已约课时数
	@Excel(name = "科二考试已约课时数", width = 20)
	private int subjectType2TestAppointmentSumTime;

	// 科二考试完成课时数
	@Excel(name = "科二考试完成课时数", width = 20)
	private int subjectType2TestCompleteSumTime;

	// 科二约考状态
	@Excel(name = "科二约考状态", width = 20)
	private String subjectTypeTest2EnrollStatus;


	// 科三常规已约课时数
	@Excel(name = "科三常规已约课时数", width = 20)
	private int subjectType3TrainAppointmentSumTime;

	// 科三常规完成课时数
	@Excel(name = "科三常规完成课时数", width = 20)
	private int subjectType3TrainCompleteSumTime;

	// 科三考试已约课时数
	@Excel(name = "科三考试已约课时数", width = 20)
	private int subjectType3TestAppointmentSumTime;

	// 科三考试完成课时数
	@Excel(name = "科三考试完成课时数", width = 20)
	private int subjectType3TestCompleteSumTime;

	// 科二约考状态
	@Excel(name = "科二约考状态", width = 20)
	private String subjectTypeTest3EnrollStatus;

	// 科二约考状态
	@Excel(name = "科二约考状态", width = 20)
	private String coachId;


	// 线上客服名称
	@Excel(name = "线上客服名称", width = 20)
	private String userName;

	// 线下客服名称
	@Excel(name = "线下客服名称", width = 20)
	private String lineUnderUserName;



	public void setCoachId(String coachId) {
		this.coachId = coachId;
		this.coachName = CoachUtil.getCoachName(coachId);
	}

	public void setUserId(String userId) {
		this.userId = userId;
		this.userName = CoachUtil.getServiceName(userId);
	}

	public void setLineUnderUserId(String lineUnderUserId) {
		this.lineUnderUserId = lineUnderUserId;
		this.lineUnderUserName =  CoachUtil.getServiceName(lineUnderUserId);
	}

	// 科二约考状态
	@Excel(name = "教练姓名", width = 20)
	private String coachName;

}