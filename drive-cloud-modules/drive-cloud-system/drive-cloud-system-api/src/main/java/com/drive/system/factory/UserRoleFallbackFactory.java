package com.drive.system.factory;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.system.api.RemoteUserRoleFeignService;
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
public class UserRoleFallbackFactory implements FallbackFactory<RemoteUserRoleFeignService> {


    @Override
    public RemoteUserRoleFeignService create(Throwable throwable) {
        return new RemoteUserRoleFeignService() {

            @Override
            public ResObject getDeptByRoleId(Long roleId) {
                return R.failure();
            }
        };
    }
}
