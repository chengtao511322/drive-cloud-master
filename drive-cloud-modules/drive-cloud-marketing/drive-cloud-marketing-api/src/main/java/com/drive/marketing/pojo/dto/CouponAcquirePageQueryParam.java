package com.drive.marketing.pojo.dto;

import lombok.Data;

/**
 * @Author 小郭
 * @Description //TODO 优惠券查询dto
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
public class CouponAcquirePageQueryParam implements java.io.Serializable{

    // 产品ID
    private String projectId;

    // 学员ID
    private String studentId;

    // 优惠券领取ID
    private String couponAcquireId;
}
