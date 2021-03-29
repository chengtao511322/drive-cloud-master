package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 学员教练评价明细表
 *
 * @author xiaoguo
 */
@Data
public class EvaluateTagAppraiseVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 评价标签id
	private String evaluateTagId;
	@Excel(name = "标签内容", width = 20)
	private String evaluateTagName;

	// 学员教练评价表id
	@Excel(name = "学员教练评价表id", width = 20)
	private String studentCoachAppraiseId;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 是否删除
	@Excel(name = "是否删除", width = 20)
	private String isDelete;

}