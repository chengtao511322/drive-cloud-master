package com.drive.basics.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import com.drive.common.core.base.BasePageQueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 菜单 按钮 用户拥有权限管理
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ChannelAuthPageQueryParam extends BasePageQueryParam {

	private String id;

	// 菜单ID
	@ApiModelProperty(value = "菜单ID")
	private String channelId;
	// 模糊查询字段

	// 用户ID
	@ApiModelProperty(value = "用户ID")
	private String userId;
	// 模糊查询字段

	// 菜单ID
	@ApiModelProperty(value = "菜单ID")
	private String tenantId;
	// 模糊查询字段

	// 创建时间
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	// 模糊查询字段

	// 修改时间
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	// 模糊查询字段

	// 创建者
	@ApiModelProperty(value = "创建者")
	private String createUser;
	// 模糊查询字段
	//private String vagueNameSearch


	private String isDelete;

	private String status;

}