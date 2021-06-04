package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.hutool.core.util.StrUtil;
import com.drive.admin.util.AdminCacheUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 一费制学员教练关联表
 *
 * @author xiaoguo
 */
@Data
public class OneFeeSystemCoachStudentVo {



	// 主键
	@Excel(name = "主键", width = 20)
	@ApiModelProperty(value = "测试111")
	private String id;

	// 教练id
	@Excel(name = "教练id", width = 20)
	@ApiModelProperty(value = "测试1222")
	private String coachId;

	@Excel(name = "教练名称", width = 20)
	@ApiModelProperty(value = "测试33")
	private String coachName;

	// 学员id

	private String studentId;
	@Excel(name = "学员名称", width = 20)
	private String studentName;

	// 订单号
	@Excel(name = "订单号", width = 20)
	private String orderNo;

	// 状态（正常，停用）
	@Excel(name = "状态（正常，停用）", width = 20)
	private String status;

	// 班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班
	@Excel(name = "班型（1-自主预约,2-vip特训，23-vip普通，4-vip包过班")
	private String classType;

	// 是否删除
	@Excel(name = "是否删除", width = 20)
	private String isDelete;

	// 备注(主要存储，停用原因)
	@Excel(name = "备注(主要存储，停用原因)", width = 20)
	private String remarks;

	// 原班班型ID
	@Excel(name = "原班班型ID", width = 20)
	private String originalClassId;

	// 现班班型ID
	@Excel(name = "现班班型ID", width = 20)
	private String classId;
	// 班型名称
	private String className;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 驾照类型
	@Excel(name = "驾照类型", width = 20)
	private String driveType;

	// 绑定时间
	@Excel(name = "绑定时间", width = 20)
	private LocalDateTime bindTime;

	// 绑定状态 (1-待绑定 ,2-已绑定，3-已解绑)
	@Excel(name = "绑定状态 (1-待绑定 ,2-已绑定，3-已解绑)", width = 20)
	private String bindStatus;

	// 解除绑定时间
	@Excel(name = "解除绑定时间", width = 20)
	private LocalDateTime relieveBindTime;

	// 新绑定关联表id
	@Excel(name = "新绑定关联表id", width = 20)
	private String newBindId;

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

	// 支付状态 (1-待支付, 2-已经支付)
	@Excel(name = "支付状态 (1-待支付, 2-已经支付)", width = 20)
	private String payStatus;

	// 科目二教练提成金额
	@Excel(name = "科目二教练提成金额", width = 20)
	private BigDecimal coachSubjectType2;

	// 科目二教练预收入金额
	@Excel(name = "科目二教练预收入金额", width = 20)
	private BigDecimal coachSubject2ExpectIncome;

	// 科目三教练提成金额
	@Excel(name = "科目三教练提成金额", width = 20)
	private BigDecimal coachSubjectType3;

	// 科目三教练预收入金额
	@Excel(name = "科目三教练预收入金额", width = 20)
	private BigDecimal coachSubject3ExpectIncome;

	// 科目二驾校提成金额
	@Excel(name = "科目二驾校提成金额", width = 20)
	private BigDecimal schoolSubjectType2;

	// 科目三驾校提成金额
	@Excel(name = "科目三驾校提成金额", width = 20)
	private BigDecimal schoolSubjectType3;

	// 运营商提成金额
	@Excel(name = "运营商提成金额", width = 20)
	private BigDecimal operatorChangeMoney;

	// 奖金（学员拿证后，教练获得的奖金）
	@Excel(name = "奖金（学员拿证后，教练获得的奖金）", width = 20)
	private BigDecimal bonus;

	// 挂科扣款金额
	@Excel(name = "挂科扣款金额", width = 20)
	private BigDecimal testNotPassWithholdMoney;

	// 平台提成金额（所有上级运营商提成金）
	@Excel(name = "平台提成金额（所有上级运营商提成金）", width = 20)
	private BigDecimal serviceChangeMoney;

	//原班型名称
	private String originalClassName;



	public void setCoachId(String coachId) {
		this.coachId = coachId;
		if (StrUtil.isNotEmpty(coachId)){
			this.coachName = AdminCacheUtil.getCoachName(coachId);
		}
	}
}