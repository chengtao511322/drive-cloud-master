package com.drive.system.pojo.dto;


import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 岗位信息
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class PostPageQueryParam extends BasePageQueryParam {


	// 岗位ID
	private Long postId;

	// 岗位编码
	private String postCode;

	// 岗位名称
	private String postName;

	// 状态（0正常 1停用）
	private String status;

	// 备注
	private String remark;

	// 排序
	private Integer orderNum;

	// 创建人
	private String createBy;

	// 创建时间
	private LocalDateTime createTime;

	// 更新人
	private String updateBy;

	// 更新时间
	private LocalDateTime updateTime;


	private String vaguePostCodeSearch;
	private String vaguePostNameSearch;

}