package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 平台应用版本表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("tb_app_version")
public class AppVersionEntity extends BaseEntity {


	private String id;

	// 应用类型（1：学员；2：教练，3-运维）
	private String appType;

	// 版本类型(ios,android)
	private String versionType;

	// 版本名称
	private String versionName;

	// 版本号
	private String version;

	// 是否强制更新
	private String isMustUpdate;

	// 下载地址
	private String downloadLocation;

	// 平台密钥
	private String appKey;

	// 版本说明
	private String versionExplain;

	// 下载次数
	private String downloadCount;

	// 创建者
	private String createUser;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新者
	private String updateUser;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}