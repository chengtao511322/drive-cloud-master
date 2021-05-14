package com.drive.admin.pojo.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 提成设置表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_deduct_setting")
public class DeductSettingEntity extends BaseEntity {


	@TableId(type= IdType.ID_WORKER)
	private String id;

	private String name;

	// 用户类型 1-个人，2-商铺,3 企业类型 4  学校类型
	private Integer userType;

	// 经理课时提成比例
	private BigDecimal managerHourProportion;

	// 经理报名提成金额
	private BigDecimal managerApplyAmount;

	// 代理商课时提成比
	private BigDecimal agencyHourProportion;

	// 代理商报名提成金额
	private BigDecimal agencyApplyAmount;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 渠道经理ID
	private String recommendManagerId;

	// 代理商ID
	private String recommendAgencyId;

}