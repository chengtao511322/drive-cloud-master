package com.drive.marketing.factory;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.marketing.api.RemoteActivityService;
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
            public ResObject pageList() {
                return R.failure();
            }
        };
    }
}
