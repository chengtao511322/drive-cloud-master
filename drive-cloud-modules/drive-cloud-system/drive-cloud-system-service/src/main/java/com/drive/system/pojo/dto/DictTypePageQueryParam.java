package com.drive.system.pojo.dto;


import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 字典类型
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class DictTypePageQueryParam extends BasePageQueryParam {

	// 字典主键
	private Long dictTypeId;

	// 字典名称
	private String dictName;

	// 字典编码
	private String dictCode;

	// 状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;

	// 创建人
	private String createBy;

	// 创建时间
	private LocalDateTime createTime;

	// 更新人
	private String updateBy;

	// 更新时间
	private LocalDateTime updateTime;

}