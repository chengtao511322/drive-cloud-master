package com.drive.admin.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 评价标签表
 *
 * @author xiaoguo
 */
@Data
public class EvaluateTagVo {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 标签名称
	@Excel(name = "标签名称", width = 20)
	private String name;

	// 状态(1-正常，2-停用)
	@Excel(name = "状态(1-正常，2-停用)", width = 20)
	private String status;

	// 订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）
	@Excel(name = "订单类型（1-学车报名；2-考试报名；3-常规训练；4-考试训练）", width = 20)
	private String orderType;

	// 评价分段（1-好评（45星），2-中评（3星），3-差评（12星））
	@Excel(name = "评价分段（1-好评（45星），2-中评（3星），3-差评（12星））", width = 20)
	private String fractionParagraph;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 是否删除(0否；1：是)
	@Excel(name = "是否删除(0否；1：是)", width = 20)
	private String isDelete;

}