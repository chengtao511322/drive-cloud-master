
package com.drive.admin.factory;

import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.vo.StudentInfoRpcVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;


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


            @Override
            public ResObject reduceInventoryRollback() {
                return R.failure();
            }

            @Override
            public ResObject<StudentInfoRpcVo> getByIdInfo(String id) {
                return R.failure();
            }

            @Override
            public ResObject<Map<String, StudentInfoRpcVo>> batchStudent(String[] ids) {
                return R.failure();
            }
        };
    }
}

