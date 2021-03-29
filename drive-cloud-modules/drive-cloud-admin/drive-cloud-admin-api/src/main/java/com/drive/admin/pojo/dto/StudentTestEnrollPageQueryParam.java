package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 学员考试报名表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class StudentTestEnrollPageQueryParam extends BasePageQueryParam {


	// 考试报名单号
	private String testEnrollNo;
	// 模糊查询字段
	//private String vagueNameSearch

	// 学员ID
	private String studentId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 实际考试场地-省
	private String provinceId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 实际考试场地-市
	private String cityId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 实际考试场地-区
	private String areaId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 驾照类型（c1；c2...）
	private String driveType;
	// 模糊查询字段
	//private String vagueNameSearch

	// 科目类型
	private String subjectType;
	// 模糊查询字段
	//private String vagueNameSearch

	// 报名单价
	private BigDecimal price;
	// 模糊查询字段
	//private String vagueNameSearch

	// 平台服务费用
	private BigDecimal serviceCharge;
	// 模糊查询字段
	//private String vagueNameSearch

	// 报名状态（1-提交报名待支付；2-支付成功；3-支付失败；4-报名失败；5-预约成功  ; 6-报名取消 ,7-考试完成,8-考试通过,9-考试不通过，10-申请中,11-退款处理中,12-退款成功）
	private String enrollStatus;
	// 模糊查询字段
	//private String vagueNameSearch

	// 希望预约考试时间
	private LocalDateTime testHopeTime;
	// 模糊查询字段
	//private String vagueNameSearch

	// 实际考试时间
	private LocalDateTime testActualTime;
	// 模糊查询字段
	//private String vagueNameSearch

	// 希望预约考试场地ID
	private String testHopeCoachingGridId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 实际预约考试场地ID
	private String testActualCoachingGridId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 创建时间
	private LocalDateTime createTime;
	// 模糊查询字段
	//private String vagueNameSearch

	// 更新时间
	private LocalDateTime updateTime;
	// 模糊查询字段
	//private String vagueNameSearch

	// 客服ID（系统用户ID）
	private String userId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 线下客服id(考试接送客服)
	private String lineUnderUserId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 是否选择接送
	private String isMeetGive;
	// 模糊查询字段
	//private String vagueNameSearch

	// 接送费用
	private BigDecimal meetGicePrice;
	// 模糊查询字段
	//private String vagueNameSearch

	// 运营商id(数据权限标记)
	private String operatorId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 考试类型(1-自主预约,2-vip特训，23-vip普通，4-vip包过班
	private Integer examType;
	// 模糊查询字段
	private String vagueTestEnrollNoSearch;

	private String testHopeTimeSearch;

	private String testActualTimeSearch;

}