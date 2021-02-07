package com.drive.admin.pojo.dto;

import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 平台训练场地表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CoachingGridPageQueryParam extends BasePageQueryParam {


	private String id;
	// 模糊查询字段
	private String vagueNameSearch;

	// 场地名称
	private String name;
	// 模糊查询字段
	//private String vagueNameSearch

	// 省
	private String provinceId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 市
	private String cityId;
	// 模糊查询字段
	private String vaguePhoneSearch;

	// 区
	private String areaId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 详细地址
	private String address;
	// 模糊查询字段
	//private String vagueNameSearch

	// 场地详情（图文）
	private String remarks;
	// 模糊查询字段
	//private String vagueNameSearch

	// 图片介绍
	private String imageUrlList;
	// 模糊查询字段
	//private String vagueNameSearch

	// 科目类型
	private String subjectType;
	// 模糊查询字段
	//private String vagueNameSearch

	// 场地类型（1-训练场地；2-考试场地）
	private String type;
	// 模糊查询字段
	//private String vagueNameSearch

	// 经度
	private String longitude;
	// 模糊查询字段
	//private String vagueNameSearch

	// 纬度
	private String latitude;
	// 模糊查询字段
	//private String vagueNameSearch

	// 场地费用/H
	private BigDecimal price;
	// 模糊查询字段
	//private String vagueNameSearch

	// 驾校id
	private String driveSchoolId;
	// 模糊查询字段
	//private String vagueNameSearch

	// 驾校名称
	private String driveSchooName;
	// 模糊查询字段
	//private String vagueNameSearch

	// 创建者
	private String createUser;
	// 模糊查询字段
	//private String vagueNameSearch

	// 更新着
	private String updateUser;
	// 模糊查询字段
	//private String vagueNameSearch

	// 创建时间
	private LocalDateTime createTime;
	// 模糊查询字段
	//private String vagueNameSearch

	// 更新时间
	private LocalDateTime updateTime;
	// 模糊查询字段
	//private String vagueNameSearch

	// 考试车价格/H
	private BigDecimal gridTestCarPrice;
	// 模糊查询字段
	//private String vagueNameSearch

	// 联系电话
	private String phone;
	// 模糊查询字段
	//private String vagueNameSearch

	// 状态（1-正常，2-停用）
	private String status;
	// 模糊查询字段
	//private String vagueNameSearch

	// 运营商id(数据权限标记)
	private String operatorId;
	// 模糊查询字段
	//private String vagueNameSearch

}