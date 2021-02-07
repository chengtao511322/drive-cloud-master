package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 教练发课设置
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CoachHourSettingPageQueryParam extends BasePageQueryParam {


	// 主键
	private String id;
	// 模糊查询字段

	// 生效时间
	private LocalDateTime effectiveTime;
	// 模糊查询字段

	// 状态(1-正常，2-停用)
	private String status;
	// 模糊查询字段

	// 是否删除
	private String isDelete;
	// 模糊查询字段

	// 创建时间
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	private String operatorId;
	// 模糊查询字段
	private String effectiveTimeSearch;

}