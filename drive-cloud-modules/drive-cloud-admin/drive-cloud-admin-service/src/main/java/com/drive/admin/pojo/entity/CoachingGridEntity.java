package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 平台训练场地表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_coaching_grid")
public class CoachingGridEntity extends BaseEntity {


	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 场地名称
	private String name;

	// 省
	private String provinceId;

	// 市
	private String cityId;

	// 区
	private String areaId;

	// 详细地址
	private String address;

	// 场地详情（图文）
	private String remarks;

	// 图片介绍
	private String imageUrlList;

	// 科目类型
	private String subjectType;

	// 场地类型（1-训练场地；2-考试场地）
	private String type;

	// 经度
	private String longitude;

	// 纬度
	private String latitude;

	// 场地费用/H
	private BigDecimal price;

	// 驾校id
	private String driveSchoolId;

	// 驾校名称
	private String driveSchooName;

	// 创建者
	private String createUser;

	// 更新着
	private String updateUser;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 考试车价格/H
	private BigDecimal gridTestCarPrice;

	// 联系电话
	private String phone;

	// 状态（1-正常，2-停用）
	private String status;

	// 运营商id(数据权限标记)
	private String operatorId;

}