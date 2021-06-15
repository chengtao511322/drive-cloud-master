package com.drive.bbs.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;

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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
 * 论坛帖子回复表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("bbs_invitation_reply")
public class InvitationReplyEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 父id(用于记录用户评价回复的内容)
	private String superId;

	// 论坛帖子表id
	private String invitationId;

	// 用户id
	private String userId;

	// 用户操作状态(1-已发布，2-已删除，3-内容违规)
	private Integer userOperateStatus;

	// 状态(1：正常；2：停用)
	private Integer status;

	// 文本内容
	private String textContent;

	// 图片路径
	private String imgUrl;

	// 视频路径
	private String videoUrl;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 回复时间
	private LocalDateTime replyTime;

	// 删除状态 1  未删除  2 已经删除
	@TableLogic
   	@TableField(value="is_delete")
	private Integer isDelete;

	// 和谐前内容
	private String rawTextContent;

}