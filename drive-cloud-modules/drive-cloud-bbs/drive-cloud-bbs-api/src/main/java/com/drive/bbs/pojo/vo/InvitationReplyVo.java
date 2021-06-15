package com.drive.bbs.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 论坛帖子回复表
 *
 * @author xiaoguo
 */
@Data
public class InvitationReplyVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 父id(用于记录用户评价回复的内容)
	@Excel(name = "父id(用于记录用户评价回复的内容)", width = 20)
	private String superId;

	// 论坛帖子表id
	@Excel(name = "论坛帖子表id", width = 20)
	private String invitationId;

	// 用户id
	@Excel(name = "用户id", width = 20)
	private String userId;

	// 用户操作状态(1-已发布，2-已删除，3-内容违规)
	@Excel(name = "用户操作状态(1-已发布，2-已删除，3-内容违规)", width = 20)
	private Integer userOperateStatus;

	// 状态(1：正常；2：停用)
	@Excel(name = "状态(1：正常；2：停用)", width = 20)
	private Integer status;

	// 文本内容
	@Excel(name = "文本内容", width = 20)
	private String textContent;

	// 图片路径
	@Excel(name = "图片路径", width = 20)
	private String imgUrl;

	// 视频路径
	@Excel(name = "视频路径", width = 20)
	private String videoUrl;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 回复时间
	@Excel(name = "回复时间", width = 20)
	private LocalDateTime replyTime;

	// 删除状态 1  未删除  2 已经删除
	@Excel(name = "删除状态 1  未删除  2 已经删除", width = 20)
	private Integer isDelete;

	// 和谐前内容
	@Excel(name = "和谐前内容", width = 20)
	private String rawTextContent;

}