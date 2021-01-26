
package com.drive.admin.factory;

import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
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
public class StudentFallbackFactory implements FallbackFactory<RemoteStudentFeignService> {


    @Override
    public RemoteStudentFeignService create(Throwable throwable) {
        return new RemoteStudentFeignService() {
            @Override
            public ResObject<StudentInfoVo> get(String id) {
                return R.failure();
            }
        };
    }
}

