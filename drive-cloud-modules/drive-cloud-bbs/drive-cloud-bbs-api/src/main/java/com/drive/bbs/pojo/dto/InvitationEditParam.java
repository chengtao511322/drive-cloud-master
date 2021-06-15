package com.drive.bbs.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * 论坛帖子表
 *
 * @author xiaoguo
 */
@Data
public class InvitationEditParam {


    // id
    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private String id;

    // 文本内容
    @NotBlank(message = "文本内容不能为空")
    @ApiModelProperty(value = "文本内容")
    private String textContent;

    // 图片路径
    @NotBlank(message = "图片路径不能为空")
    @ApiModelProperty(value = "图片路径")
    private String imgUrl;

    // 视频内容
    @ApiModelProperty(value = "视频内容")
    private String videoUrl;

    // 用户ID
    @NotBlank(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID")
    private String userId;

    // 用户操作状态(1-已发布，2-已删除，3-内容违规)
    @ApiModelProperty(value = "用户操作状态(1-已发布，2-已删除，3-内容违规)")
    private Integer userOperateStatus;

    // 当前位置
    @ApiModelProperty(value = "当前位置")
    private String address;

    // 点赞次数
    @ApiModelProperty(value = "点赞次数")
    private Integer praiseSize;

    // 收藏次数
    @ApiModelProperty(value = "收藏次数")
    private Integer collectionSize;

    // 浏览次数
    @ApiModelProperty(value = "浏览次数")
    private Integer browseSize;

    // 发布时间
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime releaseTime;

    // 创建时间
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    // 更新时间
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    // 状态(1：正常；2：停用)
    @ApiModelProperty(value = "状态(1：正常；2：停用)")
    private Integer status;

    // 删除状态 0 未删除  1 已经删除
    @ApiModelProperty(value = "删除状态 0 未删除  1 已经删除")
    private Integer isDelete;

    // 评论数
    @ApiModelProperty(value = "评论数")
    private Integer commentSize;

    // 是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏
    @ApiModelProperty(value = "是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏")
    private Long isGive;

    // 是否屏蔽  1 
    @ApiModelProperty(value = "是否屏蔽  1 ")
    private Long isShield;

    // 首次发帖标识 1 首次 2 不是
    @ApiModelProperty(value = "首次发帖标识 1 首次 2 不是")
    private Long isInitialIssue;

    // 和谐前内容（原内容）
    @ApiModelProperty(value = "和谐前内容（原内容）")
    private String rawTextContent;

    @ApiModelProperty(value = "")
    private Integer isTop;


}