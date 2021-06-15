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
 * 论坛帖子表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("bbs_invitation")
public class InvitationEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 文本内容
	private String textContent;

	// 图片路径
	private String imgUrl;

	// 视频内容
	private String videoUrl;

	// 用户ID
	private String userId;

	// 用户操作状态(1-已发布，2-已删除，3-内容违规)
	private Integer userOperateStatus;

	// 当前位置
	private String address;

	// 点赞次数
	private Integer praiseSize;

	// 收藏次数
	private Integer collectionSize;

	// 浏览次数
	private Integer browseSize;

	// 发布时间
	private LocalDateTime releaseTime;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 状态(1：正常；2：停用)
	private Integer status;

	// 删除状态 0 未删除  1 已经删除
	@TableLogic
   	@TableField(value="is_delete")
	private Integer isDelete;

	// 评论数
	private Integer commentSize;

	// 是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏
	private Long isGive;

	// 是否屏蔽  1 
	private Long isShield;

	// 首次发帖标识 1 首次 2 不是
	private Long isInitialIssue;

	// 和谐前内容（原内容）
	private String rawTextContent;

	private Integer isTop;

}