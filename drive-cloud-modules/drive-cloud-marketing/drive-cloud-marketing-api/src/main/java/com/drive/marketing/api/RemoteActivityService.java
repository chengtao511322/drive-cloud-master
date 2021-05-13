package com.drive.marketing.api;

import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import com.drive.marketing.factory.ActivityFallbackFactory;
import com.drive.marketing.pojo.dto.CouponAcquirePageQueryParam;
import com.drive.marketing.pojo.dto.CouponEditParam;
import com.drive.marketing.pojo.vo.CouponGetVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户服务
 * @author xiaoguo
 */
@FeignClient(contextId = "RemoteActivityService", value = ServiceNameConstants.MARKETING_SERVICE, fallbackFactory = ActivityFallbackFactory.class)
public interface RemoteActivityService {

    /**
     * 通过条件获取优惠券信息
     * @return
     */
    @PostMapping("/coupon/getCoupon")
    ResObject<CouponGetVo> getCoupon(@RequestBody CouponAcquirePageQueryParam couponPageQueryParam);

    /**
     * 保存优惠券
     * @param couponEditParam
     * @return
     */
    @PostMapping("/coupon")
    ResObject saveCoupon(@RequestBody CouponEditParam couponEditParam);
}
