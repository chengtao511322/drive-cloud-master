package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.common.core.tree.TreeVoFeature;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 栏目
 *
 * @author xiaoguo
 */
@Data
public class ChannelVo implements TreeVoFeature<ChannelVo> {


	// id
	@Excel(name = "id", width = 20)
	private String id;

	// 栏目名称
	@Excel(name = "栏目名称", width = 20)
	private String name;

	// 图标地址
	@Excel(name = "图标地址", width = 20)
	private String iconPath;

	// 排序
	@Excel(name = "排序", width = 20)
	private Integer sort;

	// 状态：0：未发表  1：已经发表  默认1
	@Excel(name = "状态：0：未发表  1：已经发表  默认1", width = 20)
	private String status;

	// 删除状态:0:未删除  1：已经删除  默认0
	@Excel(name = "删除状态:0:未删除  1：已经删除  默认0", width = 20)
	private String isDelete;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime updateTime;

	// 添加者
	@Excel(name = "添加者", width = 20)
	private String addUser;

	// 二级栏目
	@Excel(name = "二级栏目", width = 20)
	private String parentId;

	// 点击地址（执行函数）
	@Excel(name = "点击地址（执行函数）", width = 20)
	private String clickUrl;

	// 租户ID
	@Excel(name = "租户ID", width = 20)
	private String tenantId;

	// android显示状态：0不显示，1：显示
	@Excel(name = "android显示状态：0不显示，1：显示", width = 20)
	private String androidShow;

	// ios显示状态：0不显示，1：显示
	@Excel(name = "ios显示状态：0不显示，1：显示", width = 20)
	private String iosShow;

	// 描述
	@Excel(name = "描述", width = 20)
	private String description;

	// 活动说明
	@Excel(name = "活动说明", width = 20)
	private String activityExplain;

	// 规则说明
	@Excel(name = "规则说明", width = 20)
	private String ruleExplain;

	// 栏目
	@Excel(name = "栏目", width = 20)
	private String columnGroup;

	// 口令
	@Excel(name = "口令", width = 20)
	private String command;

	// 权限
	@Excel(name = "权限", width = 20)
	private String auth;

	private Boolean  hasChildren;



	/** 子部门 */
	private List<ChannelVo> children = new ArrayList<ChannelVo>();

	@Override
	public void putChildrenList(List<ChannelVo> children) {
		this.children = children;
	}

	@Override
	public List<ChannelVo> getChildrenList() {
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