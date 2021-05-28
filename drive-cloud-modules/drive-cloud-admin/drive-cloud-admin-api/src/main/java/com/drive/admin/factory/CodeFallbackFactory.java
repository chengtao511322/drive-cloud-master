
package com.drive.admin.factory;

import com.drive.admin.api.RemoteCodeFeignService;
import com.drive.admin.api.RemoteStudentFeignService;
import com.drive.admin.pojo.dto.CodePageQueryParam;
import com.drive.admin.pojo.vo.CodeVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 服务降级处理
 *
 * @author DreamChan
 */

@Component
@Slf4j
public class CodeFallbackFactory implements FallbackFactory<RemoteCodeFeignService> {


    @Override
    public RemoteCodeFeignService create(Throwable throwable) {
        return new RemoteCodeFeignService() {


            @Override
            public ResObject<List<CodeVo>> findList(CodePageQueryParam param) {
                return R.failure();
            }
        };
    }
}

