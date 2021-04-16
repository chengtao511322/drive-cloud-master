package com.drive.basics.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 菜单 按钮 用户拥有权限管理
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("base_channel_auth")
public class ChannelAuthEntity extends BaseEntity {

	//
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 菜单ID
	private String channelId;

	// 用户ID
	private String userId;

	// 菜单ID
	private String tenantId;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 创建者
	private String createUser;

	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private String isDelete;

	private String status;

}