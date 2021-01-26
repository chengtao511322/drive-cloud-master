package com.drive.marketing.pojo.dto;

import lombok.Data;


/**
 * 
 *
 * @author xiaoguo
 */
@Data
public class CouponProductRelationEditParam {


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