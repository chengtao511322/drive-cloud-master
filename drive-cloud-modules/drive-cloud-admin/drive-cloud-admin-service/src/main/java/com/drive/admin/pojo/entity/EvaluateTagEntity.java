package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 评价标签表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("evaluate_tag")
public class EvaluateTagEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 标签名称
	private String name;

	// 状态(1-正常，2-停用)
	private String status;

	// 订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	private String orderType;

	// 评价分段（1-好评（45星），2-中评（3星），3-差评（12星））
	private String fractionParagraph;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 是否删除(0否；1：是)
	@TableLogic
  	@TableField(value="is_delete")
	private String isDelete;

}