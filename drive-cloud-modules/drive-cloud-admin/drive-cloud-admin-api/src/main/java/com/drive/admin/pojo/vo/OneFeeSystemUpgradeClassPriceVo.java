package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.admin.util.AdminCacheUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 一费制学员升班价格控制表
 *
 * @author xiaoguo
 */
@Data
public class OneFeeSystemUpgradeClassPriceVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 原班类型id
	@Excel(name = "原班类型id", width = 20)
	private String originalClassId;
	private String originalClassName;
	private OneFeeSystemPriceVo originalClassVo;

	// 原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）
	@Excel(name = "原班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）", width = 20)
	private String originalClassType;

	// 升级班类型id
	@Excel(name = "升级班类型id", width = 20)
	private String upgradeClassId;
	private String upgradeClassName;
	private OneFeeSystemPriceVo upgradeClassVo;

	// 升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）
	@Excel(name = "升级班类型（1-vip包过，2-vip普通，3-vip特训,4-自主预约）", width = 20)
	private String upgradeClassType;

	// 升班班型价格
	@Excel(name = "升班班型价格", width = 20)
	private BigDecimal upgradePrice;

	// 升班价格详情介绍
	@Excel(name = "升班价格详情介绍", width = 20)
	private String detailsUrl;

	// 是否删除（0-否，1-是）
	@Excel(name = "是否删除（0-否，1-是）", width = 20)
	private Integer isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 运营商提成金额
	@Excel(name = "运营商提成金额", width = 20)
	private BigDecimal operatorChangeMoney;

	// 平台提成金额（所有上级运营商提成金）
	@Excel(name = "平台提成金额（所有上级运营商提成金）", width = 20)
	private BigDecimal serviceChangeMoney;

	// 状态(1-正常，2-停用)
	@Excel(name = "状态(1-正常，2-停用)", width = 20)
	private String status;


	public void setOriginalClassId(String originalClassId) {
		this.originalClassId = originalClassId;
		this.originalClassName = AdminCacheUtil.getClassName(originalClassId);
		this.originalClassVo = AdminCacheUtil.getClassVo(originalClassId);
	}

	public void setUpgradeClassId(String upgradeClassId) {
		this.upgradeClassId = upgradeClassId;
		this.upgradeClassName = AdminCacheUtil.getClassName(upgradeClassId);
		this.upgradeClassVo = AdminCacheUtil.getClassVo(upgradeClassId);
	}
}