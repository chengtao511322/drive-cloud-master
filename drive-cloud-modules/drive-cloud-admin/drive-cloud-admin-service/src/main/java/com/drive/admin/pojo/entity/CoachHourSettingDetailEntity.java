package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.drive.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * 运营商教练课时设置表
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_coach_hour_setting_detail")
public class CoachHourSettingDetailEntity extends BaseEntity {


	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 时间段(1-上午;2-下午;3-晚上)
	private String timeSection;

	// 开始时间(只存时分，所以使用字符串)
	private String startTime;

	// 结束时间(只存时分，所以使用字符串)
	private String endTime;

	// 运营商ID
	private String operatorId;

	// 创建者
	private String createUser;

	// 创建时间  系统自动创建
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	// 修改时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	// 是否删除
	@TableLogic
	@TableField(fill = FieldFill.INSERT,value="is_delete")
	private String isDelete;

	private String hourSettingId;

}