package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 平台发送短信表
 *
 * @author xiaoguo
 */
@Data
public class MessageVo {


	@Excel(name = "", width = 20)
	private String id;

	// 手机号码
	@Excel(name = "手机号码", width = 20)
	private String phone;

	// 验证码
	@Excel(name = "验证码", width = 20)
	private String code;

	@Excel(name = "", width = 20)
	private LocalDateTime createtime;

	// 有效时间
	@Excel(name = "有效时间", width = 20)
	private LocalDateTime expiretime;

	// 备注
	@Excel(name = "备注", width = 20)
	private String remarks;

	// 运营商id(数据权限标记)
	@Excel(name = "运营商id(数据权限标记)", width = 20)
	private String operatorId;

}