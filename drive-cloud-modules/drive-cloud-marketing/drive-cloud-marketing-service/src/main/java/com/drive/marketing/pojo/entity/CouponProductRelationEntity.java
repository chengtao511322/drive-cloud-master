package com.drive.marketing.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 
 *
 * @author xiaoguo
 */
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("mark_coupon_product_relation")
public class CouponProductRelationEntity implements Serializable {


	@TableId(type= IdType.ID_WORKER)
	private String id;

	// 优惠券ID
	private String couponId;

	// 产品ID
	private String productId;

	// 类型
	private Long type;
	// 类型名称
	private String typeName;
	// 产品名称
	private String productName;

}