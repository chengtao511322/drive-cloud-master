package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
 * 系统配置参数表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author chentao
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("sys_param")
public class SysParamEntity extends BaseEntity {


	// 参数枚举编号
	@TableId(type = IdType.INPUT)
	private String prmEnumId;

	// 参数名称
	private String prmName;

	// 参数中文涵义
	private String prmCnName;

	// 参数数值
	private String prmValue;

	// 参数备注
	private String prmRemark;

	// 参数状态，0-正常 1-停用
	private String prmStatus;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 创建人
	private String createUser;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 修改人
	private String updateUser;

}