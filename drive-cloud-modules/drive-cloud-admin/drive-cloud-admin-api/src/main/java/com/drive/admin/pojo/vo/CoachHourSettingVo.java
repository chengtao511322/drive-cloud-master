package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 教练发课设置
 *
 * @author xiaoguo
 */
@Data
public class CoachHourSettingVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 生效时间
	@Excel(name = "生效时间", width = 20)
	private LocalDateTime effectiveTime;

	// 状态(1-正常，2-停用)
	@Excel(name = "状态(1-正常，2-停用)", width = 20)
	private String status;

	// 是否删除
	@Excel(name = "是否删除", width = 20)
	private String isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}