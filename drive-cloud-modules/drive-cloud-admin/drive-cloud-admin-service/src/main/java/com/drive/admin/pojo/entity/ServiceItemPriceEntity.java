package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 服务项目价格表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author JoyoDuan
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_service_item_price")
public class ServiceItemPriceEntity extends BaseEntity {


	// 主键id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 服务类型（报名、科一、科二、科三、科四）
	private String serviceType;

	// t_platform_service_item表的主键id
	private String serviceItemId;

	// 服务项价格
	private BigDecimal price;

	// 状态（1、启用，0、停用）
	private String status;

	// 是否删除（1、是，0、否）
	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private String isDelete;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;
	// 服务项描述，表述该服务项的具体含义，此字段只有在运营商有特殊需求是才会使用，没否则代码中应该默认使用‘平台项目表中的表述’;例如：体检面签费是什么？
	private String serviceItemDescribe;

	// 价格描述
	private String priceDescribe;

}