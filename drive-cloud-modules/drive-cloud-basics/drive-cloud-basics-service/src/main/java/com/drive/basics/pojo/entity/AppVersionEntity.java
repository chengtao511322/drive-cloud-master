package com.drive.basics.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * app版本控制信息表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("base_app_version")
public class AppVersionEntity extends BaseEntity {


	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 应用类型（1：学员；2：教练，3-运维）
	private Integer appType;

	// 版本类型(2ios,1android)
	private Integer versionType;

	// 版本名称
	private String versionName;

	// 版本号
	private String version;

	// 是否强制更新  0  否  1 是
	private Integer isMustUpdate;

	// 下载地址
	private String downloadLocation;

	// 平台密钥
	private String appKey;

	// 版本说明
	private String versionExplain;

	// 下载次数
	private Integer downloadCount;

	// 创建者
	private String createUser;

	// 修改着
	private String updateUser;

	// 发布时间
	private LocalDateTime publishTime;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}