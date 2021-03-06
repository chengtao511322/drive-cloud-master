package com.drive.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 字典类型
 *
 * @author xiaoguo
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_dict_type")
public class DictTypeEntity extends BaseEntity {


	// 字典主键
	@TableId(value = "dict_type_id" , type = IdType.AUTO)
	private Long dictTypeId;

	// 字典名称
	private String dictName;

	// 字典编码
	private String dictCode;

	// 状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;


}