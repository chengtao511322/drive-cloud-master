package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 推广人员信息表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_recommend_user")
public class RecommendUserEntity extends BaseEntity {


	// id
	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 渠道经理表id
	private String managerId;

	// 推广商类型（1-个人，2-商铺）
	private String userType;

	// 学员id
	private String studentId;

	// 名称(商店，个人，组织)
	private String name;

	// 备注
	private String remarks;

	// 状态(1-待审核，2-通过，3-驳回)
	private String status;

	// 经度
	private String longitude;

	// 纬度
	private String latitude;

	// 详细地址
	private String address;

	// 是否删除(0-否，1-是)
	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private String isDelete;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 课时提成百分比
	private BigDecimal classTiemPercent;

	// 运营商id(数据权限标记)
	private String operatorId;

	// 报名分成金额
	private BigDecimal applyDivideAmount;

	// VIP报名分成金额
	private BigDecimal vipApplyDivideAmount;

	// VIP课时提成百分比
	private BigDecimal vipClassTiemPercent;

}