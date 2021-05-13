package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 推广渠道经理
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class RecommendManagerPageQueryParam extends BasePageQueryParam {


	// id
	private String id;

	// 学员id
	private String studentId;

	// 备注
	private String remarks;

	// 创建时间
	private LocalDateTime createTime;

	// 更新时间
	private LocalDateTime updateTime;

	// 是否删除(0-否，1-是)
	private String isDelete;

	// 状态(1-正常，2-停用)
	private String status;

	// 运营商id(数据权限标记)
	private String operatorId;

	private String vagueRemarksSearch;

}