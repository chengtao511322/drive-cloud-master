package com.drive.bbs.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 论坛帖子表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class InvitationPageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 文本内容
	@ApiModelProperty(value = "文本内容")
	private String textContent;

	// 模糊查询字段

	// 图片路径
	@ApiModelProperty(value = "图片路径")
	private String imgUrl;
	// 模糊查询字段

	// 视频内容
	@ApiModelProperty(value = "视频内容")
	private String videoUrl;
	// 模糊查询字段

	// 用户ID
	@ApiModelProperty(value = "用户ID")
	private String userId;
	// 模糊查询字段

	// 用户操作状态(1-已发布，2-已删除，3-内容违规)
	@ApiModelProperty(value = "用户操作状态(1-已发布，2-已删除，3-内容违规)")
	private Integer userOperateStatus;
	// 模糊查询字段

	// 当前位置
	@ApiModelProperty(value = "当前位置")
	private String address;
	// 模糊查询字段

	// 点赞次数
	@ApiModelProperty(value = "点赞次数")
	private Integer praiseSize;
	// 模糊查询字段

	// 收藏次数
	@ApiModelProperty(value = "收藏次数")
	private Integer collectionSize;
	// 模糊查询字段

	// 浏览次数
	@ApiModelProperty(value = "浏览次数")
	private Integer browseSize;
	// 模糊查询字段

	// 发布时间
	@ApiModelProperty(value = "发布时间")
	private LocalDateTime releaseTime;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 状态(1：正常；2：停用)
	@ApiModelProperty(value = "状态(1：正常；2：停用)")
	private Integer status;
	// 模糊查询字段

	// 删除状态 0 未删除  1 已经删除
	@ApiModelProperty(value = "删除状态 0 未删除  1 已经删除")
	private Integer isDelete;
	// 模糊查询字段

	// 评论数
	@ApiModelProperty(value = "评论数")
	private Integer commentSize;
	// 模糊查询字段

	// 是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏
	@ApiModelProperty(value = "是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏")
	private Long isGive;
	// 模糊查询字段

	// 是否屏蔽  1 
	@ApiModelProperty(value = "是否屏蔽  1 ")
	private Long isShield;
	// 模糊查询字段

	// 首次发帖标识 1 首次 2 不是
	@ApiModelProperty(value = "首次发帖标识 1 首次 2 不是")
	private Long isInitialIssue;
	// 模糊查询字段

	// 和谐前内容（原内容）
	@ApiModelProperty(value = "和谐前内容（原内容）")
	private String rawTextContent;
	// 模糊查询字段

	@ApiModelProperty(value = "")
	private Integer isTop;
	// 模糊查询字段
	//private String vagueNameSearch

	// 模糊查询
	private String vagueTextContentSearch;

	private String vagueRawTextContentSearch;

}