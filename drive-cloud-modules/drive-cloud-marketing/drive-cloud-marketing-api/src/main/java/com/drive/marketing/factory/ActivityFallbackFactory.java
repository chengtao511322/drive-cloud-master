package com.drive.marketing.factory;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.marketing.api.RemoteActivityService;
import com.drive.marketing.pojo.dto.CouponAcquirePageQueryParam;
import com.drive.marketing.pojo.dto.CouponEditParam;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理
 *
 * @author DreamChan
 */
@Component
@Slf4j
public class ActivityFallbackFactory implements FallbackFactory<RemoteActivityService> {


    @Override
    public RemoteActivityService create(Throwable throwable) {
        return new RemoteActivityService() {
            @Override
            public ResObject getCoupon(CouponAcquirePageQueryParam couponPageQueryParam) {
                return R.failure();
            }

            @Override
            public ResObject saveCoupon(CouponEditParam couponEditParam) {
                return R.failure();
            }
        };
    }
}
