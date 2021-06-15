package com.drive.bbs.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 用户点赞表
 *
 * @author xiaoguo
 */
@Data
public class UserLikeEditParam {


    @NotBlank(message = "不能为空")
    @ApiModelProperty(value = "")
    private String id;

    // 被点赞的用户id
    @NotBlank(message = "被点赞的用户id不能为空")
    @ApiModelProperty(value = "被点赞的用户id")
    private String likedUserId;

    // 点赞的用户id
    @NotBlank(message = "点赞的用户id不能为空")
    @ApiModelProperty(value = "点赞的用户id")
    private String likedPostId;

    // 点赞状态，0取消，1点赞
    @NotBlank(message = "点赞状态，0取消，1点赞不能为空")
    @ApiModelProperty(value = "点赞状态，0取消，1点赞")
    private Boolean status;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 修改时间
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    // 点赞时间
    @NotBlank(message = "点赞时间不能为空")
    @ApiModelProperty(value = "点赞时间")
    private LocalDateTime likedTime;

    // 帖子ID
    @NotBlank(message = "帖子ID不能为空")
    @ApiModelProperty(value = "帖子ID")
    private String invitationId;


}