package com.drive.system.pojo.vo;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 字典数据
 *
 * @author xiaoguo
 */
@Data
public class DictItemVo implements java.io.Serializable {


	// 字典编码
	private Long dictItemId;

	// 字典类型
	@Excel(name = "字典类型", width = 20)
	private String dictCode;

	// 字典项名称
	@Excel(name = "字典项名称", width = 20)
	private String itemName;

	// 字典项键值
	@Excel(name = "字典项键值", width = 20)
	private String itemValue;

	// 状态（0正常 1停用）
	private String status;

	// 备注
	@Excel(name = "备注", width = 20)
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