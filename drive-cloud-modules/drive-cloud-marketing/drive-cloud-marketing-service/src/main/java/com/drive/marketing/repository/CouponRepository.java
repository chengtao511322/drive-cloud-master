package com.drive.marketing.repository;

import com.drive.common.core.biz.ResObject;
import com.drive.marketing.pojo.dto.CouponAcquirePageQueryParam;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.marketing.pojo.dto.CouponPageQueryParam;
import com.drive.marketing.pojo.vo.CouponGetVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    /**
     * 条件查询获取优惠券信息
     * @param couponPageQueryParam
     * @return
     */

    ResObject<CouponGetVo> getCoupon(@PathVariable @RequestBody CouponAcquirePageQueryParam couponPageQueryParam);
}
