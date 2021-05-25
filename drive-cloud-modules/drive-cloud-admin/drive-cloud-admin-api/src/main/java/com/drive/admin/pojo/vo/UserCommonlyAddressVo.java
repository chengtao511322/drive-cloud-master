package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 用户常用地址关联表
 *
 * @author xiaoguo
 */
@Data
public class UserCommonlyAddressVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 用户id
	@Excel(name = "用户id", width = 20)
	private String userId;
	private String userName;

	// 用户类型(1-学员 2-教练  3-客服 )
	@Excel(name = "用户类型(1-学员 2-教练  3-客服 )", width = 20)
	private String userType;

	// 地址名称
	@Excel(name = "地址名称", width = 20)
	private String address;

	// 地址纬度
	@Excel(name = "地址纬度", width = 20)
	private String latitude;

	// 地址经度
	@Excel(name = "地址经度", width = 20)
	private String longitude;

	// 是否默认为地址（是，否）
	@Excel(name = "是否默认为地址（是，否）", width = 20)
	private String isDefault;

	// 状态(正常，停用)
	@Excel(name = "状态(正常，停用)", width = 20)
	private String status;

	// 备注
	@Excel(name = "备注", width = 20)
	private String remarks;

	// 注册时间
	@Excel(name = "注册时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 场地ID
	@Excel(name = "场地ID", width = 20)
	private String siteId;

	private String coachingGridName;

	// 科目类型
	@Excel(name = "科目类型", width = 20)
	private Integer subjectType;

}