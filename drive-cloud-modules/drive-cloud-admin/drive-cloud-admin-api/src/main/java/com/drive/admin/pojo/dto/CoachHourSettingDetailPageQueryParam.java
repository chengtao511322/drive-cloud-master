package com.drive.admin.pojo.dto;

import com.drive.common.core.base.BasePageQueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 运营商教练课时设置表
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class CoachHourSettingDetailPageQueryParam extends BasePageQueryParam {


	private String id;
	// 模糊查询字段

	// 时间段(1-上午;2-下午;3-晚上)
	private String timeSection;
	// 模糊查询字段

	// 开始时间(只存时分，所以使用字符串)
	private String startTime;
	// 模糊查询字段

	// 结束时间(只存时分，所以使用字符串)
	private String endTime;
	// 模糊查询字段

	// 运营商ID
	private String operatorId;
	// 模糊查询字段

	// 创建者
	private String createUser;
	// 模糊查询字段

	// 创建时间  系统自动创建
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 是否删除
	private String isDelete;

	@ApiModelProperty(value = "发课主表ID")
	private String hourSettingId;
	// 模糊查询字段
	//private String vagueNameSearch

}