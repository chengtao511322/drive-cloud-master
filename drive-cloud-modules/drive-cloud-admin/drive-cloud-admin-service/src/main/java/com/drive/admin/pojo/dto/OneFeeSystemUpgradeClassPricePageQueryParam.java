package com.drive.admin.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 一费制学员升班价格控制表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OneFeeSystemUpgradeClassPricePageQueryParam extends BasePageQueryParam {


	// id
	@ApiModelProperty(value = "id")
	private String id;
	// 模糊查询字段

	// 原班类型id
	@ApiModelProperty(value = "原班类型id")
	private String originalClassId;
	// 模糊查询字段

	// 原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）
	@ApiModelProperty(value = "原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）")
	private String originalClassType;
	// 模糊查询字段

	// 升级班类型id
	@ApiModelProperty(value = "升级班类型id")
	private String upgradeClassId;
	// 模糊查询字段

	// 升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）
	@ApiModelProperty(value = "升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）")
	private String upgradeClassType;
	// 模糊查询字段

	// 升班班型价格
	@ApiModelProperty(value = "升班班型价格")
	private BigDecimal upgradePrice;
	// 模糊查询字段

	// 升班价格详情介绍
	@ApiModelProperty(value = "升班价格详情介绍")
	private String detailsUrl;
	// 模糊查询字段

	// 是否删除（0-否，1-是）
	@ApiModelProperty(value = "是否删除（0-否，1-是）")
	private Integer isDelete;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 更新时间
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 运营商id(数据权限标记)
	@ApiModelProperty(value = "运营商id(数据权限标记)")
	private String operatorId;
	// 模糊查询字段

	// 运营商提成金额
	@ApiModelProperty(value = "运营商提成金额")
	private BigDecimal operatorChangeMoney;
	// 模糊查询字段

	// 平台提成金额（所有上级运营商提成金）
	@ApiModelProperty(value = "平台提成金额（所有上级运营商提成金）")
	private BigDecimal serviceChangeMoney;
	// 模糊查询字段

	// 状态(1-正常，2-停用)
	@ApiModelProperty(value = "状态(1-正常，2-停用)")
	private String status;
	// 模糊查询字段  原班型名称
	private String vagueOriginalClassNameSearch;
	private String vagueUpgradeClassNameSearch;
	private String[] createDateTimeSearchArr = new String[2];

}