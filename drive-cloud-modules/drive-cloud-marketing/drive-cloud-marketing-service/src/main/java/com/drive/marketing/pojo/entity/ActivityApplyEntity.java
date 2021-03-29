package com.drive.marketing.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 活动参加记录表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("activity_apply")
public class ActivityApplyEntity extends BaseEntity {


	private String id;

	// 用户名称
	private String userName;

	// 手机号
	private String phone;

	// 活动名称
	private String activityName;

	private String userId;

	// 活动ID
	private String activityId;

	private LocalDateTime applyTime;

	// 创建者
	private String addUser;

	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 启用状态 0  未启用  1 已经启用
	private Integer status;

	// 删除状态 0 未删除1删除
	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private Integer isDelete;

	// 运营位置 ，存放省市区编码，多个用逗号隔开
	private String operatingPosition;

	private String operator;

	// 租户ID
	private String tenantId;

	private String promoteUserId;

	private String projectId;

}