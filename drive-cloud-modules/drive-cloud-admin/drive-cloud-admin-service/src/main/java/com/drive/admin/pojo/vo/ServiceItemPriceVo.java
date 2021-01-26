package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 服务项目价格表
 *
 * @author JoyoDuan
 */
@Data
public class ServiceItemPriceVo {


	// 主键id
	@Excel(name = "主键id", width = 20)
	private String id;

	// 服务类型（报名、科一、科二、科三、科四）
	@Excel(name = "服务类型（报名、科一、科二、科三、科四）", width = 20)
	private String serviceType;

	// t_platform_service_item表的主键id
	@Excel(name = "t_platform_service_item表的主键id", width = 20)
	private String serviceItemId;

	// 服务项目名称
	private String serviceItemName;

	// 服务项价格
	@Excel(name = "服务项价格", width = 20)
	private BigDecimal price;

	// 状态（1、启用，0、停用）
	@Excel(name = "状态（1、启用，0、停用）", width = 20)
	private String status;

	// 是否删除（1、是，0、否）
	@Excel(name = "是否删除（1、是，0、否）", width = 20)
	private String isDelete;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 服务项描述，表述该服务项的具体含义，此字段只有在运营商有特殊需求是才会使用，没否则代码中应该默认使用‘平台项目表中的表述’;例如：体检面签费是什么？
	private String serviceItemDescribe;


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
	// 价格描述
	private String serviceItemPriceDescribe;

	// 服务；类型
	private String serviceItemType;

	// 服务明细ID
	private String servicePackageItemId;

	// 价格描述
	private String priceDescribe;


}