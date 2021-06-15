package com.drive.bbs.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 论坛帖子表
 *
 * @author xiaoguo
 */
@Data
public class InvitationVo implements java.io.Serializable{


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 文本内容
	@Excel(name = "文本内容", width = 20)
	private String textContent;

	// 图片路径
	@Excel(name = "图片路径", width = 20)
	private String imgUrl;

	// 视频内容
	@Excel(name = "视频内容", width = 20)
	private String videoUrl;

	// 用户ID
	@Excel(name = "用户ID", width = 20)
	private String userId;

	// 用户操作状态(1-已发布，2-已删除，3-内容违规)
	@Excel(name = "用户操作状态(1-已发布，2-已删除，3-内容违规)", width = 20)
	private Integer userOperateStatus;

	// 当前位置
	@Excel(name = "当前位置", width = 20)
	private String address;

	// 点赞次数
	@Excel(name = "点赞次数", width = 20)
	private Integer praiseSize;

	// 收藏次数
	@Excel(name = "收藏次数", width = 20)
	private Integer collectionSize;

	// 浏览次数
	@Excel(name = "浏览次数", width = 20)
	private Integer browseSize;

	// 发布时间
	@Excel(name = "发布时间", width = 20)
	private LocalDateTime releaseTime;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 状态(1：正常；2：停用)
	@Excel(name = "状态(1：正常；2：停用)", width = 20)
	private Integer status;

	// 删除状态 0 未删除  1 已经删除
	@Excel(name = "删除状态 0 未删除  1 已经删除", width = 20)
	private Integer isDelete;

	// 评论数
	@Excel(name = "评论数", width = 20)
	private Integer commentSize;

	// 是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏
	@Excel(name = "是否打赏过  1 未处理2同意打赏3不同意打赏4已打赏", width = 20)
	private Long isGive;

	// 是否屏蔽  1 
	@Excel(name = "是否屏蔽  1 ", width = 20)
	private Long isShield;

	// 首次发帖标识 1 首次 2 不是
	@Excel(name = "首次发帖标识 1 首次 2 不是", width = 20)
	private Long isInitialIssue;

	// 和谐前内容（原内容）
	@Excel(name = "和谐前内容（原内容）", width = 20)
	private String rawTextContent;

	@Excel(name = "", width = 20)
	private Integer isTop;

}