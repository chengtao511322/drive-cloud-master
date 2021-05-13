package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 运营商加盟申请
 *
 * @author xiaoguo
 */
@Data
public class OperatorRecruitVo {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 省
	@Excel(name = "省", width = 20)
	private String provinceId;

	// 市
	@Excel(name = "市", width = 20)
	private String cityId;

	// 区
	@Excel(name = "区", width = 20)
	private String areaId;

	// 姓名
	@Excel(name = "姓名", width = 20)
	private String realName;

	// 电话
	@Excel(name = "电话", width = 20)
	private String phone;

	// 邮箱
	@Excel(name = "邮箱", width = 20)
	private String eMail;

	// 公司名称
	@Excel(name = "公司名称", width = 20)
	private String corporationName;

	// 申请说明
	@Excel(name = "申请说明", width = 20)
	private String applyExplain;

	// 是否删除
	@Excel(name = "是否删除", width = 20)
	private String isDelete;

	// 注册时间
	@Excel(name = "注册时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

}