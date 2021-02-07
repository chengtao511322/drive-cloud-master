package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 运营商教练课时设置表
 *
 * @author xiaoguo
 */
@Data
public class CoachHourSettingDetailVo {


	@Excel(name = "", width = 20)
	private String id;

	// 时间段(1-上午;2-下午;3-晚上)
	@Excel(name = "时间段(1-上午;2-下午;3-晚上)", width = 20)
	private String timeSection;

	// 开始时间(只存时分，所以使用字符串)
	@Excel(name = "开始时间(只存时分，所以使用字符串)", width = 20)
	private String startTime;

	// 结束时间(只存时分，所以使用字符串)
	@Excel(name = "结束时间(只存时分，所以使用字符串)", width = 20)
	private String endTime;

	// 运营商ID
	@Excel(name = "运营商ID", width = 20)
	private String operatorId;

	// 创建者
	@Excel(name = "创建者", width = 20)
	private String createUser;

	// 创建时间  系统自动创建
	@Excel(name = "创建时间  系统自动创建", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 是否删除
	@Excel(name = "是否删除", width = 20)
	private String isDelete;

	@ApiModelProperty(value = "发课主表ID")
	private String hourSettingId;

}