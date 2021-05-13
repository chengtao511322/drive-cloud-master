package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.common.core.tree.TreeVoFeature;
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
public class OperatorVo implements TreeVoFeature<OperatorVo> {


	// 主键
	@Excel(name = "主键", width = 20)
	private String id;

	// 运营商名称
	@Excel(name = "运营商名称", width = 20)
	private String name;

	// 运营商级别(1-省，2-市，3-区(县))
	@Excel(name = "运营商级别(1-省，2-市，3-区(县))", width = 20)
	private Integer level;

	// 平台提成百分比
	@Excel(name = "平台提成百分比", width = 20)
	private BigDecimal platformChargePercent;

	// 是否删除
	@Excel(name = "是否删除", width = 20)
	private String isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 更新时间
	@Excel(name = "更新时间", width = 20)
	private LocalDateTime updateTime;

	// 使用状态（1：正常；2：停用）
	@Excel(name = "使用状态（1：正常；2：停用）", width = 20)
	private String status;

	// 父级代理商id
	@Excel(name = "父级代理商id", width = 20)
	private String parentId;

	// 分配课时绩效比例
	@Excel(name = "分配课时绩效比例", width = 20)
	private BigDecimal hourPerformanceRatio;

	// 其他benaid
	@Excel(name = "其他benaid", width = 20)
	private String channelBeanId;

	/** 子部门 */
	private List<OperatorVo> children = new ArrayList<OperatorVo>();

	@Override
	public void putChildrenList(List<OperatorVo> children) {
		this.children = children;
	}

	@Override
	public List<OperatorVo> getChildrenList() {
		return this.children;
	}

	@Override
	public String findNodeId() {
		return id;
	}

	@Override
	public String findParentNodeId() {
		return parentId;
	}

	@Override
	public String getTreeLabel() {
		return this.getName();
	}
}