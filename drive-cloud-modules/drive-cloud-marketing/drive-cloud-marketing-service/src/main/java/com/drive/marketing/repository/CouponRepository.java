package com.drive.marketing.repository;

import com.drive.common.core.biz.ResObject;
import com.drive.marketing.pojo.dto.CouponEditParam;

public interface CouponRepository {

    /**
     * 发布优惠券
     * @param couponEditParam
     * @return
     */
    ResObject publishCoupon(CouponEditParam couponEditParam);

    /**
     * 发布优惠券
     * @param couponEditParam
     * @return
     */
    ResObject updateCoupon(CouponEditParam couponEditParam);
}
