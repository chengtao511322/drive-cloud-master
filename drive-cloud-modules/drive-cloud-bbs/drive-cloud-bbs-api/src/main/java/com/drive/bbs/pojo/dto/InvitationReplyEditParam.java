package com.drive.bbs.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


/**
 * 论坛帖子回复表
 *
 * @author xiaoguo
 */
@Data
public class InvitationReplyEditParam {


    // id
    @NotBlank(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private String id;

    // 父id(用于记录用户评价回复的内容)
    @NotBlank(message = "父id(用于记录用户评价回复的内容)不能为空")
    @ApiModelProperty(value = "父id(用于记录用户评价回复的内容)")
    private String superId;

    // 论坛帖子表id
    @NotBlank(message = "论坛帖子表id不能为空")
    @ApiModelProperty(value = "论坛帖子表id")
    private String invitationId;

    // 用户id
    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private String userId;

    // 用户操作状态(1-已发布，2-已删除，3-内容违规)
    @NotBlank(message = "用户操作状态(1-已发布，2-已删除，3-内容违规)不能为空")
    @ApiModelProperty(value = "用户操作状态(1-已发布，2-已删除，3-内容违规)")
    private Integer userOperateStatus;

    // 状态(1：正常；2：停用)
    @NotBlank(message = "状态(1：正常；2：停用)不能为空")
    @ApiModelProperty(value = "状态(1：正常；2：停用)")
    private Integer status;

    // 文本内容
    @NotBlank(message = "文本内容不能为空")
    @ApiModelProperty(value = "文本内容")
    private String textContent;

    // 图片路径
    @ApiModelProperty(value = "图片路径")
    private String imgUrl;

    // 视频路径
    @ApiModelProperty(value = "视频路径")
    private String videoUrl;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 回复时间
    @NotBlank(message = "回复时间不能为空")
    @ApiModelProperty(value = "回复时间")
    private LocalDateTime replyTime;

    // 删除状态 1  未删除  2 已经删除
    @ApiModelProperty(value = "删除状态 1  未删除  2 已经删除")
    private Integer isDelete;

    // 和谐前内容
    @NotBlank(message = "和谐前内容不能为空")
    @ApiModelProperty(value = "和谐前内容")
    private String rawTextContent;


}