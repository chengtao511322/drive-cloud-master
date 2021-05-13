package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 运营商代理区域
 *
 * @author xiaoguo
 */
@Data
public class OperatorAreaVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 代理商id
	@Excel(name = "代理商id", width = 20)
	private String operatorId;

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
	private String isUpSelect;

	// 是否删除
	@Excel(name = "是否删除", width = 20)
	private Integer isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

}