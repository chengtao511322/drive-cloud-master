package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 学车视频表
 *
 * @author xiaoguo
 */
@Data
public class DrivingVideoVo {


	@Excel(name = "", width = 20)
	private String id;

	// 是否对外开放  0 否 1 是
	@Excel(name = "是否对外开放  0 否 1 是", width = 20)
	private Integer isOpen;

	// 名称
	@Excel(name = "名称", width = 20)
	private String name;

	// 标题
	@Excel(name = "标题", width = 20)
	private String title;

	// 描述
	@Excel(name = "描述", width = 20)
	private String description;

	// 缩略图路径
	@Excel(name = "缩略图路径", width = 20)
	private String thumb;

	// 视频链接
	@Excel(name = "视频链接", width = 20)
	private String link;

	// 文件路径
	@Excel(name = "文件路径", width = 20)
	private String path;

	// 文件链接
	@Excel(name = "文件链接", width = 20)
	private String url;

	// 文件类型
	@Excel(name = "文件类型", width = 20)
	private Integer ext;

	// 文件大小
	@Excel(name = "文件大小", width = 20)
	private String size;

	// 文件md5
	@Excel(name = "文件md5", width = 20)
	private String md5;

	// sha1 散列值
	@Excel(name = "sha1 散列值", width = 20)
	private String sha1;

	// 来源 0未知 1 安卓 2 iOS 3WEB 4微信 5PC WEB 6桌面版Linux 7桌面macOS 8桌面Windows
	@Excel(name = "来源 0未知 1 安卓 2 iOS 3WEB 4微信 5PC WEB 6桌面版Linux 7桌面macOS 8桌面Windows", width = 20)
	private Integer fromPlatform;

	// 排序
	@Excel(name = "排序", width = 20)
	private Integer sort;

	// 发布状态0未发布 1发布
	@Excel(name = "发布状态0未发布 1发布", width = 20)
	private Integer status;

	// 删除状态 0  未删除 1  已经删除
	@Excel(name = "删除状态 0  未删除 1  已经删除", width = 20)
	private Integer isDelete;

	// 上传时间
	@Excel(name = "上传时间", width = 20)
	private LocalDateTime uploadTime;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 模块名 
	@Excel(name = "模块名 ", width = 20)
	private String module;

	// 类型  1  科目一 2  科目二 3 科目三 4  科目四  0 所有
	@Excel(name = "类型  1  科目一 2  科目二 3 科目三 4  科目四  0 所有", width = 20)
	private Integer type;

}