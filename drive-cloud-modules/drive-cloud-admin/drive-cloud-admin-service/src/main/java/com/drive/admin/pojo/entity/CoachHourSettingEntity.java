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
 * 教练发课设置
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 *
 *   @TableLogic
 *        @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_coach_hour_setting")
public class CoachHourSettingEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 生效时间
	private LocalDateTime effectiveTime;

	// 状态(1-正常，2-停用)
	private String status;

	// 是否删除
	@TableLogic
	@TableField(value="is_delete")
	private String isDelete;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	private String operatorId;

}