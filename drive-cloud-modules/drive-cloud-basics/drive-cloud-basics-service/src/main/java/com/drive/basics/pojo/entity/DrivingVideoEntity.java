package com.drive.basics.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 学车视频表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("base_driving_video")
public class DrivingVideoEntity extends BaseEntity {


	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 是否对外开放  0 否 1 是
	private Integer isOpen;

	// 名称
	private String name;

	// 标题
	private String title;

	// 描述
	private String description;

	// 缩略图路径
	private String thumb;

	// 视频链接
	private String link;

	// 文件路径
	private String path;

	// 文件链接
	private String url;

	// 文件类型
	private Integer ext;

	// 文件大小
	private String size;

	// 文件md5
	private String md5;

	// sha1 散列值
	private String sha1;

	// 来源 0未知 1 安卓 2 iOS 3WEB 4微信 5PC WEB 6桌面版Linux 7桌面macOS 8桌面Windows
	private Integer fromPlatform;

	// 排序
	private Integer sort;

	// 发布状态0未发布 1发布
	private Integer status;

	// 删除状态 0  未删除 1  已经删除
	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private Integer isDelete;

	// 上传时间
	private LocalDateTime uploadTime;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 模块名 
	private String module;

	// 类型  1  科目一 2  科目二 3 科目三 4  科目四  0 所有
	private Integer type;

}