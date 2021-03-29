package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 教练授课区域表
 *   主键ID 需要加入这个
 *   @TableId(type= IdType.ID_WORKER)
 *   @TableLogic
 *  @TableField(value="is_delete")
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_coach_give_area")
public class CoachGiveAreaEntity extends BaseEntity {


	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 教练ID
	private String coachId;

	// 省级编码
	private String provinceId;

	// 市级编码
	private String cityId;

	// 区(县)级编码
	private String areaId;

	// 是否向上查询（价格,教练在当前区划查询不到时）
	private Integer isUpSelect;

	// 创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

}