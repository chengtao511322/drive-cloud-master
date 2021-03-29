package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台训练场地表
 *
 * @author xiaoguo
 */
@Data
public class CoachingGridVo {


	@Excel(name = "", width = 20)
	private String id;

	// 场地名称
	@Excel(name = "场地名称", width = 20)
	private String name;

	// 省
	@Excel(name = "省", width = 20)
	private String provinceId;

	// 市
	@Excel(name = "市", width = 20)
	private String cityId;

	// 区
	@Excel(name = "区", width = 20)
	private String areaId;

	// 详细地址
	@Excel(name = "详细地址", width = 20)
	private String address;

	// 场地详情（图文）
	@Excel(name = "场地详情（图文）", width = 20)
	private String remarks;

	// 图片介绍
	@Excel(name = "图片介绍", width = 20)
	private String imageUrlList;

	// 科目类型
	@Excel(name = "科目类型", width = 20)
	private String subjectType;

	// 场地类型（1-训练场地；2-考试场地）
	@Excel(name = "场地类型（1-训练场地；2-考试场地）", width = 20)
	private String type;

	// 经度
	@Excel(name = "经度", width = 20)
	private String longitude;

	// 纬度
	@Excel(name = "纬度", width = 20)
	private String latitude;

	// 场地费用/H
	@Excel(name = "场地费用/H", width = 20)
	private BigDecimal price;

	// 驾校id
	@Excel(name = "驾校id", width = 20)
	private String driveSchoolId;

	// 驾校名称
	@Excel(name = "驾校名称", width = 20)
	private String driveSchooName;

	// 创建者
	@Excel(name = "创建者", width = 20)
	private String createUser;

	// 更新着
	@Excel(name = "更新着", width = 20)
	private String updateUser;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 考试车价格/H
	@Excel(name = "考试车价格/H", width = 20)
	private BigDecimal gridTestCarPrice;

	// 联系电话
	@Excel(name = "联系电话", width = 20)
	private String phone;

	// 状态（1-正常，2-停用）
	@Excel(name = "状态（1-正常，2-停用）", width = 20)
	private String status;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}