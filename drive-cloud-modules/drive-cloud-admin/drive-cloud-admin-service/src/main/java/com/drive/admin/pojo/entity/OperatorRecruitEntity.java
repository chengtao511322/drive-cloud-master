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
 * 运营商加盟申请
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_operator_recruit")
public class OperatorRecruitEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 省
	private String provinceId;

	// 市
	private String cityId;

	// 区
	private String areaId;

	// 姓名
	private String realName;

	// 电话
	private String phone;

	// 邮箱
	private String eMail;

	// 公司名称
	private String corporationName;

	// 申请说明
	private String applyExplain;

	// 是否删除
	@TableLogic
  	@TableField(value="is_delete")
	private String isDelete;

	// 注册时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}