package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 服务项目打包明细表
 *
 * @author xiaoguo
 */
@Data
public class ServicePackageDetailVo {


	// 主键id
	@Excel(name = "主键id", width = 20)
	private String id;

	// 服务项目包（打包表）id
	@Excel(name = "服务项目包（打包表）id", width = 20)
	private String servicePackageId;

	// 服务项目价格表id
	@Excel(name = "服务项目价格表id", width = 20)
	private String serviceItemPriceId;

	// 状态（1、启用，0、停用）
	@Excel(name = "状态（1、启用，0、停用）", width = 20)
	private String status;

	// 是否删除（1、是，0、否）
	@Excel(name = "是否删除（1、是，0、否）", width = 20)
	private String isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// （单位 1-个，2-次，3-公里）
	private String unit;
	// (单位值，int 类型)
	private String unitNumber;
	// 没有单位描述，当单位无法满足时，填写的替换单位的描述内容，例如： 全程接送
	private String noUnitDescribe;

	// 单位值描述，描述这个值的具体含义：例如-正考的含义
	private String unitDescribe;
	// (服务提供者：1-平台，2-教练)
	private String serviceProvider;
	// 价值
	private BigDecimal value;
	// 服务描述
	private String serviceDescription;

	// 服务；类型
	private String serviceItemType;

}