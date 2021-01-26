package com.drive.member.factory;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.member.api.RemoteStudentService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StudentFallbackFactory implements FallbackFactory<RemoteStudentService> {
    @Override
    public RemoteStudentService create(Throwable throwable) {
        return new RemoteStudentService() {
            @Override
            public ResObject get(String username) {
                return R.failure("调用出错");
            }
        };
    }
}
