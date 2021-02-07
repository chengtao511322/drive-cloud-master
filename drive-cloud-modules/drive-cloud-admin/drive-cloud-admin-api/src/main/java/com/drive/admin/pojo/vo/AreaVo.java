package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * 
 *
 * @author xiaoguo
 */
@Data
public class AreaVo {


	// 区域编码
	@Excel(name = "区域编码", width = 20)
	private String baCode;

	// 区域名称
	@Excel(name = "区域名称", width = 20)
	private String baName;

	@Excel(name = "", width = 20)
	private String baParentId;

}