package com.drive.basics.pojo.vo;

import com.drive.common.core.enums.StatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 运营商基础信息
 *
 * @author xiaoguo
 */
@Data
public class OperatorVo implements java.io.Serializable {


	// 主键
	private String id;

	// 运营商名称
	private String name;

	// 运营商级别(1-省，2-市，3-区(县))
	private Integer level;

	// 平台提成百分比
	private BigDecimal platformChargePercent;

	// 是否删除
	private String isDelete;

	// 创建时间
	private LocalDateTime createTime;

	// 更新时间
	private LocalDateTime updateTime;

	// 使用状态（1：正常；2：停用）
	private StatusEnum status;

	// 父级代理商id
	private String parentId;

	// 分配课时绩效比例
	private BigDecimal hourPerformanceRatio;

	// 其他benaid
	private String channelBeanId;

	/** 子部门 */
	private List<OperatorVo> children = new ArrayList<OperatorVo>();


}