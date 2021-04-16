package com.drive.basics.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 流程信息管理
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("base_drive_flow")
public class DriveFlowEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 标题
	private String title;

	private String context;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 删除状态 0未删除  1 ：删除
	@TableLogic
	@TableField(value="is_delete")
	private Integer isDelete;

	// 启用状态 0  未启用 1 已经启用
	private Integer status;

	// 栏目ID
	private String channelId;

	// 排序
	private Integer sort;

	// 添加者
	private String addUser;

	// 运营位置 ，存放省市区编码，多个用逗号隔开
	private String operatingPosition;

	// 租户ID
	private String tenantId;
	// 类型 1 学车流程 2 平台承诺
	private Integer type;

}