package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 推广人员信息表
 *
 * @author xiaoguo
 */
@Data
public class RecommendUserVo implements java.io.Serializable {


	// id
	//@Excel(name = "id", width = 20)
	private String id;

	// 渠道经理表id
	//@Excel(name = "渠道经理表id", width = 20)
	private String managerId;

	@ApiModelProperty(value = "渠道经理名称")
	private String managerName;

	// 推广商类型（1-个人，2-商铺）

	private String userType;
	@Excel(name = "推广商类型", width = 20)
	@ApiModelProperty(value = "推广商类型")
	private String userTypeName;

	// 学员id
	private String studentId;

	@ApiModelProperty(value = "对应学员名称")
	private String studentName;
	private String userName;

	// 名称(商店，个人，组织)
	@Excel(name = "推广商名称", width = 20,orderNum = "1", isImportField = "true_major,true_recommendUser")
	@ApiModelProperty(value = "推广商名称")
	private String name;

	// 备注
	@Excel(name = "备注", width = 20)
	@ApiModelProperty(value = "备注")
	private String remarks;

	// 状态(1-待审核，2-通过，3-驳回)
	//@Excel(name = "状态(1-待审核，2-通过，3-驳回)", width = 20)
	private String status;

	// 经度
	//@Excel(name = "经度", width = 20)
	private String longitude;

	// 纬度
	//@Excel(name = "纬度", width = 20)
	private String latitude;

	// 详细地址
	@Excel(name = "详细地址", width = 20)
	@ApiModelProperty(value = "详细地址")
	private String address;

	// 是否删除(0-否，1-是)
	//@Excel(name = "是否删除(0-否，1-是)", width = 20)
	private String isDelete;

	// 创建时间
	//@Excel(name = "创建时间", width = 20)
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	// 更新时间
	//@Excel(name = "更新时间", width = 20)
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	// 课时提成百分比
	//@Excel(name = "课时提成百分比", width = 20)
	@ApiModelProperty(value = "课时提成百分比")
	private BigDecimal classTiemPercent;

	// 运营商id(数据权限标记)
	//@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	// 报名分成金额
	//@Excel(name = "报名分成金额", width = 20)
	@ApiModelProperty(value = "报名分成金额")
	private BigDecimal applyDivideAmount;

	// VIP报名分成金额
	//@Excel(name = "VIP报名分成金额", width = 20)
	@ApiModelProperty(value = "VIP报名分成金额")
	private BigDecimal vipApplyDivideAmount;

	// VIP课时提成百分比
	//@Excel(name = "VIP课时提成百分比", width = 20)
	@ApiModelProperty(value = "VIP课时提成百分比")
	private BigDecimal vipClassTiemPercent;

	@Excel(name = "手机号码", width = 20)
	@ApiModelProperty(value = "推广商手机号码")
	private String phone;

	public void setUserType(String userType) {
		this.userType = userType;
		// （1-个人，2-商铺）
		if (userType.equals("1")){
			this.userTypeName = "个人";
		}
		if (userType.equals("2")){
			this.userTypeName = "商铺";
		}
	}
}