package com.drive.admin.pojo.entity;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 一费制学员升班价格控制表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("one_fee_system_upgrade_class_price")
public class OneFeeSystemUpgradeClassPriceEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 原班类型id
	private String originalClassId;

	// 原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）
	private String originalClassType;

	// 升级班类型id
	private String upgradeClassId;

	// 升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）
	private String upgradeClassType;

	// 升班班型价格
	private BigDecimal upgradePrice;

	// 升班价格详情介绍
	private String detailsUrl;

	// 是否删除（0-否，1-是）
	@TableLogic
   @TableField(value="is_delete")
	private Integer isDelete;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 运营商提成金额
	private BigDecimal operatorChangeMoney;

	// 平台提成金额（所有上级运营商提成金）
	private BigDecimal serviceChangeMoney;

	// 状态(1-正常，2-停用)
	private String status;

}