package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
import com.baomidou.mybatisplus.annotation.FieldFill;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 用户常用地址关联表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_user_commonly_address")
public class UserCommonlyAddressEntity extends BaseEntity {


	// 主键
	private String id;

	// 用户id
	@TableField("user_Id" )
	private String userId;

	// 用户类型(1-学员 2-教练  3-客服 )
	private String userType;

	// 地址名称
	private String address;

	// 地址纬度
	private String latitude;

	// 地址经度
	private String longitude;

	// 是否默认为地址（是，否）
	private String isDefault;

	// 状态(正常，停用)
	private String status;

	// 备注
	private String remarks;

	// 注册时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 场地ID
	private String siteId;

	// 科目类型
	private Integer subjectType;

}