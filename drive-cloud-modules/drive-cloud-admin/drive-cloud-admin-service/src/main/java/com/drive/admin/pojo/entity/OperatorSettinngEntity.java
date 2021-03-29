package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 运营商参数设置表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_operator_settinng")
public class OperatorSettinngEntity extends BaseEntity {


	// 主键
	private String id;

	// 编号(使用 00000 的形式来添加)
	private String number;

	// 编号对应值
	private String numberValue;

	// 编号中文描述
	private String numberDescribe;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 状态(1-正常，2-停用)
	private String status;

	// 是否删除（0-否，1-是）
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private String isDelete;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}