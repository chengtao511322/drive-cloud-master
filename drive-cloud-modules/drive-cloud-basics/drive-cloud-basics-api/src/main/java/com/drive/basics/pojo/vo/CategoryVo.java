package com.drive.basics.pojo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.drive.common.core.tree.TreeVoFeature;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 产品分类表
 *
 * @author xiaoguo
 */
@Data
public class CategoryVo implements TreeVoFeature<CategoryVo> {


	// 主键id
	@Excel(name = "主键id", width = 20)
	private Long id;

	// 分类名称
	@Excel(name = "分类名称", width = 20)
	private String name;

	// 父级分类id   没有父级 则为0
	@Excel(name = "父级分类id   没有父级 则为0", width = 20)
	private Long parentId;

	// 类型id，只有分类是三级分类的时候才有 一级和二级分类没有
	@Excel(name = "类型id，只有分类是三级分类的时候才有 一级和二级分类没有", width = 20)
	private Long typeId;

	// 分类的层级 1 一级 2 二级 3 三级
	@Excel(name = "分类的层级 1 一级 2 二级 3 三级", width = 20)
	private String grade;

	// 分类扣率 三级分类的时候才有，主要是和店铺对账使用
	@Excel(name = "分类扣率 三级分类的时候才有，主要是和店铺对账使用", width = 20)
	private BigDecimal rate;

	// 排序  数值越低  排序越前
	@Excel(name = "排序  数值越低  排序越前", width = 20)
	private Integer sort;

	// 删除标记  0未删除 1删除 默认0
	@Excel(name = "删除标记  0未删除 1删除 默认0", width = 20)
	private String delFlag;

	// 创建者名称
	@Excel(name = "创建者名称", width = 20)
	private String createName;

	// 修改者名称
	@Excel(name = "修改者名称", width = 20)
	private String modifyName;

	// 删除者名称
	@Excel(name = "删除者名称", width = 20)
	private String delName;

	// 创建时间
	@Excel(name = "创建时间", width = 20)
	private LocalDateTime createTime;

	// 修改时间
	@Excel(name = "修改时间", width = 20)
	private LocalDateTime modifyTime;

	// 删除时间
	@Excel(name = "删除时间", width = 20)
	private LocalDateTime delTime;

	@Excel(name = "", width = 20)
	private String dictValue;

	@Excel(name = "", width = 20)
	private String dictType;

	/** 子菜单 */
	private List<CategoryVo> children = new ArrayList<CategoryVo>();

	@Override
	public void putChildrenList(List<CategoryVo> children) {

	}

	@Override
	public List<CategoryVo> getChildrenList() {
		return this.children;
	}

	@Override
	public String findNodeId() {
		return String.valueOf(this.id);
	}

	@Override
	public String findParentNodeId() {
		return String.valueOf(this.parentId);
	}

	@Override
	public String getTreeLabel() {
		return this.name;
	}
}
