package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 推广人员信息表
 *
 * @author xiaoguo
 */
@Data
public class RecommendUserVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 渠道经理表id
	@Excel(name = "渠道经理表id", width = 20)
	private String managerId;

	// 推广商类型（1-个人，2-商铺）
	@Excel(name = "推广商类型（1-个人，2-商铺）", width = 20)
	private String userType;

	// 学员id
	@Excel(name = "学员id", width = 20)
	private String studentId;

	// 名称(商店，个人，组织)
	@Excel(name = "名称(商店，个人，组织)", width = 20)
	private String name;

	// 备注
	@Excel(name = "备注", width = 20)
	private String remarks;

	// 状态(1-待审核，2-通过，3-驳回)
	@Excel(name = "状态(1-待审核，2-通过，3-驳回)", width = 20)
	private String status;

	// 经度
	@Excel(name = "经度", width = 20)
	private String longitude;

	// 纬度
	@Excel(name = "纬度", width = 20)
	private String latitude;

	// 详细地址
	@Excel(name = "详细地址", width = 20)
	private String address;

	// 是否删除(0-否，1-是)
	@Excel(name = "是否删除(0-否，1-是)", width = 20)
	private String isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 课时提成百分比
	@Excel(name = "课时提成百分比", width = 20)
	private BigDecimal classTiemPercent;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 报名分成金额
	@Excel(name = "报名分成金额", width = 20)
	private BigDecimal applyDivideAmount;

	// VIP报名分成金额
	@Excel(name = "VIP报名分成金额", width = 20)
	private BigDecimal vipApplyDivideAmount;

	// VIP课时提成百分比
	@Excel(name = "VIP课时提成百分比", width = 20)
	private BigDecimal vipClassTiemPercent;

}