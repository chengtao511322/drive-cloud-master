package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 推广渠道经理
 *
 * @author xiaoguo
 */
@Data
public class RecommendManagerVo implements java.io.Serializable{


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 学员id
	@Excel(name = "学员id", width = 20)
	private String studentId;
	private String studentName;
	private String studentRealName;
	// 学员手机
	private String studentPhone;

	// 备注
	@Excel(name = "备注", width = 20)
	private String remarks;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 是否删除(0-否，1-是)
	@Excel(name = "是否删除(0-否，1-是)", width = 20)
	private String isDelete;

	// 状态(1-正常，2-停用)
	@Excel(name = "状态(1-正常，2-停用)", width = 20)
	private String status;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

	@Excel(name = "手机号", width = 20)
	private String phone;

	@Excel(name = "姓名", width = 20)
	private String realName;

}