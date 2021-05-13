package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.admin.util.AdminCacheUtil;
import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 一费制vip教练
 *
 * @author xiaoguo
 */
@Data
public class OneFeeSystemVipCoachVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 教练id
	@Excel(name = "教练id", width = 20)
	private String coachId;

	// 入驻时间
	@Excel(name = "入驻时间", width = 20)
	private LocalDateTime joinTime;

	// 状态（正常，停用）
	@Excel(name = "状态（正常，停用）", width = 20)
	private String stutas;

	// 备注(主要存储，停用原因)
	@Excel(name = "备注(主要存储，停用原因)", width = 20)
	private String remarks;

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

	// 预收入单价
	@Excel(name = "预收入单价", width = 20)
	private BigDecimal expectIncomePrice;

	// 教练提成百分比
	@Excel(name = "教练提成百分比", width = 20)
	private BigDecimal coachChargePercent;

	private CoachInfoVo coachInfoVo;

	// 公车提成百分比（驾校收入）
	@Excel(name = "公车提成百分比（驾校收入）", width = 20)
	private BigDecimal carChargePercent;

	private String coachName;

	private String coachPhone;

	private Integer coachAge;

	public void setCoachId(String coachId) {
		this.coachId = coachId;
		this.coachInfoVo = AdminCacheUtil.getCoachInfo(coachId);
	}
}