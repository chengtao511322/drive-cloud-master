package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * app版本控制信息表
 *
 * @author xiaoguo
 */
@Data
public class AppVersionVo {


	@Excel(name = "", width = 20)
	private String id;

	// 应用类型（1：学员；2：教练，3-运维）
	@Excel(name = "应用类型（1：学员；2：教练，3-运维）", width = 20)
	private Integer appType;

	// 版本类型(2ios,1android)
	@Excel(name = "版本类型(2ios,1android)", width = 20)
	private Integer versionType;

	// 版本名称
	@Excel(name = "版本名称", width = 20)
	private String versionName;

	// 版本号
	@Excel(name = "版本号", width = 20)
	private String version;

	// 是否强制更新  0  否  1 是
	@Excel(name = "是否强制更新  0  否  1 是", width = 20)
	private Integer isMustUpdate;

	// 下载地址
	@Excel(name = "下载地址", width = 20)
	private String downloadLocation;

	// 平台密钥
	@Excel(name = "平台密钥", width = 20)
	private String appKey;

	// 版本说明
	@Excel(name = "版本说明", width = 20)
	private String versionExplain;

	// 下载次数
	@Excel(name = "下载次数", width = 20)
	private Integer downloadCount;

	// 创建者
	@Excel(name = "创建者", width = 20)
	private String createUser;

	// 修改着
	@Excel(name = "修改着", width = 20)
	private String updateUser;

	// 发布时间
	@Excel(name = "发布时间", width = 20)
	private LocalDateTime publishTime;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

}