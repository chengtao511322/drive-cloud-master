package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 服务项目表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author JoyoDuan
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_service_item")
public class ServiceItemEntity extends BaseEntity {

	// 主键id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 服务名称
	private String name;

	// 服务类型（报名、科一、科二、科三、科四）
	private String serviceType;

	// 是否删除（1、是，0、否）
	@TableLogic
	@TableField(value="is_delete")
	private String isDelete;

	// 状态（1、启用，0、停用）
	private String status;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 服务项描述，表述该服务项的具体含义 。 例如：体检面签费是什么？
	private String serviceItemDescribe;

}