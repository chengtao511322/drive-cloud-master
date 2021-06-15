package com.drive.bbs.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户点赞表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserLikePageQueryParam extends BasePageQueryParam {


	@ApiModelProperty(value = "")
	private String id;
	// 模糊查询字段

	// 被点赞的用户id
	@ApiModelProperty(value = "被点赞的用户id")
	private String likedUserId;
	// 模糊查询字段

	// 点赞的用户id
	@ApiModelProperty(value = "点赞的用户id")
	private String likedPostId;
	// 模糊查询字段

	// 点赞状态，0取消，1点赞
	@ApiModelProperty(value = "点赞状态，0取消，1点赞")
	private Boolean status;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 点赞时间
	@ApiModelProperty(value = "点赞时间")
	private LocalDateTime likedTime;
	// 模糊查询字段

	// 帖子ID
	@ApiModelProperty(value = "帖子ID")
	private String invitationId;
	// 模糊查询字段
	//private String vagueNameSearch

}