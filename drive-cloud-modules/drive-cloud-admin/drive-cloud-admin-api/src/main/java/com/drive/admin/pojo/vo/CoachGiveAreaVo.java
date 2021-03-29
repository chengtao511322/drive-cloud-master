package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 教练授课区域表
 *
 * @author xiaoguo
 */
@Data
public class CoachGiveAreaVo {


	@Excel(name = "", width = 20)
	private String id;

	// 教练ID
	@Excel(name = "教练ID", width = 20)
	private String coachId;

	// 省级编码
	@Excel(name = "省级编码", width = 20)
	private String provinceId;

	// 市级编码
	@Excel(name = "市级编码", width = 20)
	private String cityId;

	// 区(县)级编码
	@Excel(name = "区(县)级编码", width = 20)
	private String areaId;

	// 是否向上查询（价格,教练在当前区划查询不到时）
	@Excel(name = "是否向上查询（价格,教练在当前区划查询不到时）", width = 20)
	private Integer isUpSelect;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;


	private String provinceName;
	private String cityName;
	private String areaName;

}