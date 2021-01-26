package com.drive.marketing.pojo.dto;

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
public class CouponProductRelationPageQueryParam extends BasePageQueryParam {


	private String id;

	// 优惠券ID
	private String couponId;

	// 产品ID
	private String productId;

	// 类型
	private Long type;


	private String typeName;
	private String productName;

}