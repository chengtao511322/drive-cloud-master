package com.drive.bbs.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 用户点赞表
 *
 * @author xiaoguo
 */
@Data
public class UserLikeVo {


	@Excel(name = "", width = 20)
	private String id;

	// 被点赞的用户id
	@Excel(name = "被点赞的用户id", width = 20)
	private String likedUserId;

	// 点赞的用户id
	@Excel(name = "点赞的用户id", width = 20)
	private String likedPostId;

	// 点赞状态，0取消，1点赞
	@Excel(name = "点赞状态，0取消，1点赞", width = 20)
	private Boolean status;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 点赞时间
	@Excel(name = "点赞时间", width = 20)
	private LocalDateTime likedTime;

	// 帖子ID
	@Excel(name = "帖子ID", width = 20)
	private String invitationId;

}