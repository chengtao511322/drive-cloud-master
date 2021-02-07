package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 平台应用版本表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AppVersionPageQueryParam extends BasePageQueryParam {


	private String id;
	// 模糊查询字段

	// 应用类型（1：学员；2：教练，3-运维）
	private String appType;
	// 模糊查询字段

	// 版本类型(ios,android)
	private String versionType;
	// 模糊查询字段

	// 版本名称
	private String versionName;
	// 模糊查询字段

	// 版本号
	private String version;
	// 模糊查询字段

	// 是否强制更新
	private String isMustUpdate;
	// 模糊查询字段

	// 下载地址
	private String downloadLocation;
	// 模糊查询字段

	// 平台密钥
	private String appKey;
	// 模糊查询字段

	// 版本说明
	private String versionExplain;
	// 模糊查询字段

	// 下载次数
	private String downloadCount;
	// 模糊查询字段

	// 创建者
	private String createUser;
	// 模糊查询字段

	// 创建时间
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新者
	private String updateUser;
	// 模糊查询字段

	// 更新时间
	private LocalDateTime updateTime;
	// 模糊查询字段
	private String vagueVersionNameSearch;

	private String vagueVersionSearch;

}