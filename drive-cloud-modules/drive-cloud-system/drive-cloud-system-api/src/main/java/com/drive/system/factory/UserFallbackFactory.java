package com.drive.system.factory;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.system.api.RemoteUserService;
import com.drive.system.pojo.UserInfo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理
 *
 * @author xiaoguo
 */
@Component
@Slf4j
public class UserFallbackFactory implements FallbackFactory<RemoteUserService> {


    @Override
    public RemoteUserService create(Throwable throwable) {

        return new RemoteUserService() {
            @Override
            public ResObject<UserInfo> info(String username) {
                log.error("异常");
                return R.failure();
            }
        };
    }
}
