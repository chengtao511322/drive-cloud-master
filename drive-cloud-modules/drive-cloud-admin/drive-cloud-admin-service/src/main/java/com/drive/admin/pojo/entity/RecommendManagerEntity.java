package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 推广渠道经理
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_recommend_manager")
public class RecommendManagerEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 学员id
	private String studentId;

	// 备注
	private String remarks;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 是否删除(0-否，1-是)
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
	private String isDelete;

	// 状态(1-正常，2-停用)
	private String status;

	// 运营商id(数据权限标记)
	private String operatorId;

}