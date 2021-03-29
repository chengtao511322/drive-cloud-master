package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class AreaPageQueryParam extends BasePageQueryParam {


	// 区域编码
	private String baCode;

	// 区域名称
	private String baName;

	private String baParentId;

}