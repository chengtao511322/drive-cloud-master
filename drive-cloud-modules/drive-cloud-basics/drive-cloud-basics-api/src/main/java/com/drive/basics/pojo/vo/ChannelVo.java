package com.drive.basics.pojo.vo;

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
	private String id;

	// 栏目名称
	private String name;

	// 图标地址
	private String iconPath;

	// 排序
	private Integer sort;

	// 状态：0：未发表  1：已经发表  默认1
	private Integer status;

	// 删除状态:0:未删除  1：已经删除  默认0
	private Integer isDelete;

	// 创建时间
	private LocalDateTime createTime;

	// 修改时间
	private LocalDateTime updateTime;

	// 添加者
	private String addUser;

	// 二级栏目
	private String parentId;

	// 点击地址（执行函数）
	private String clickUrl;

	// 租户ID
	private String tenantId;

	// android显示状态：0不显示，1：显示
	private Integer androidShow;

	// ios显示状态：0不显示，1：显示
	private Integer iosShow;

	// 描述
	private String description;

	// 活动说明
	private String activityExplain;

	// 规则说明
	private String ruleExplain;

	// 栏目
	private String columnGroup;

	// 口令
	private String command;

	// 权限
	private String auth;



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