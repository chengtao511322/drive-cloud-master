package com.drive.basics.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 运营商代理区域
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("base_operator_area")
public class OperatorAreaEntity extends BaseEntity {


	// 主键
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 代理商id
	private String operatorId;

	// 省级编码
	private String provinceId;

	// 市级编码
	private String cityId;

	// 区(县)级编码
	private String areaId;

	// 是否向上查询（价格,教练在当前区划查询不到时）
	private String isUpSelect;

	// 是否删除
	@TableLogic
   @TableField(value="is_delete")
	private Integer isDelete;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}