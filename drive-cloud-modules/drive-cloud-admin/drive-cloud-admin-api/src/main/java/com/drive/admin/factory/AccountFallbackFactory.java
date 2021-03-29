/*
package com.drive.admin.factory;

import com.drive.admin.api.RemoteAccountFeignService;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountFallbackFactory  implements FallbackFactory<RemoteAccountFeignService> {
    @Override
    public RemoteAccountFeignService create(Throwable throwable) {
        return new RemoteAccountFeignService() {

            @Override
            public ResObject increaseAmount(String account) {
                return R.failure("服务降价");
            }
        };
    }
}
*/
