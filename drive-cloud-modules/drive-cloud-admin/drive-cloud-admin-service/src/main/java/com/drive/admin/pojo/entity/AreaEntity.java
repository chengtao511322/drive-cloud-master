package com.drive.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 *   主键ID 需要加入这个@TableId(type= IdType.ID_WORKER)
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("t_base_area")
public class AreaEntity implements java.io.Serializable{


	// 区域编码
	private String baCode;

	// 区域名称
	private String baName;

	private String baParentId;

}