package com.drive.bbs.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 论坛帖子回复表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class InvitationReplyPageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 父id(用于记录用户评价回复的内容)
	@ApiModelProperty(value = "父id(用于记录用户评价回复的内容)")
	private String superId;
	// 模糊查询字段

	// 论坛帖子表id
	@ApiModelProperty(value = "论坛帖子表id")
	private String invitationId;
	// 模糊查询字段

	// 用户id
	@ApiModelProperty(value = "用户id")
	private String userId;
	// 模糊查询字段

	// 用户操作状态(1-已发布，2-已删除，3-内容违规)
	@ApiModelProperty(value = "用户操作状态(1-已发布，2-已删除，3-内容违规)")
	private Integer userOperateStatus;
	// 模糊查询字段

	// 状态(1：正常；2：停用)
	@ApiModelProperty(value = "状态(1：正常；2：停用)")
	private Integer status;
	// 模糊查询字段

	// 文本内容
	@ApiModelProperty(value = "文本内容")
	private String textContent;
	// 模糊查询字段

	// 图片路径
	@ApiModelProperty(value = "图片路径")
	private String imgUrl;
	// 模糊查询字段

	// 视频路径
	@ApiModelProperty(value = "视频路径")
	private String videoUrl;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 回复时间
	@ApiModelProperty(value = "回复时间")
	private LocalDateTime replyTime;
	// 模糊查询字段

	// 删除状态 1  未删除  2 已经删除
	@ApiModelProperty(value = "删除状态 1  未删除  2 已经删除")
	private Integer isDelete;
	// 模糊查询字段

	// 和谐前内容
	@ApiModelProperty(value = "和谐前内容")
	private String rawTextContent;
	// 模糊查询字段
	private String vagueTextContentSearch;

}