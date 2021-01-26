package com.drive.basics.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 产品分类表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("base_category")
public class CategoryEntity extends BaseEntity {


	// 主键id
	@TableId(value = "id" , type = IdType.AUTO)
	private Long id;

	// 分类名称
	private String name;

	// 父级分类id   没有父级 则为0
	private Long parentId;

	// 类型id，只有分类是三级分类的时候才有 一级和二级分类没有
	private Long typeId;

	// 分类的层级 1 一级 2 二级 3 三级
	private String grade;

	// 分类扣率 三级分类的时候才有，主要是和店铺对账使用
	private BigDecimal rate;

	// 排序  数值越低  排序越前
	private Integer sort;

	// 删除标记  0未删除 1删除 默认0
	@TableLogic
	@TableField(value="del_flag")
	private String delFlag;

	// 创建者名称
	private String createName;

	// 修改者名称
	private String modifyName;

	// 删除者名称
	private String delName;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	private LocalDateTime modifyTime;

	// 删除时间
	private LocalDateTime delTime;

	private String dictValue;

	private String dictType;

	// 创建者
	@TableField(fill = FieldFill.INSERT)
	private String createBy;

	// 修改者
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private String updateBy;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}