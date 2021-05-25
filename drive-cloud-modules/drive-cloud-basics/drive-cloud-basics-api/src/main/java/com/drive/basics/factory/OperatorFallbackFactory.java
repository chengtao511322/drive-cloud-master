package com.drive.basics.factory;

import com.drive.basics.feign.RemoteOperatorFeignService;
import com.drive.basics.pojo.dto.OperatorEditParam;
import com.drive.basics.pojo.vo.OperatorVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OperatorFallbackFactory implements FallbackFactory<RemoteOperatorFeignService> {

    @Override
    public RemoteOperatorFeignService create(Throwable throwable) {
        return new RemoteOperatorFeignService(){
            @Override
            public ResObject<OperatorVo> get(String id) {
                return R.failure();
            }

            @Override
            public ResObject saveOperator(OperatorEditParam operatorEditParam) {
                return R.failure();
            }

            @Override
            public ResObject updateOperator(OperatorEditParam operatorEditParam) {
                return null;
            }

            @Override
            public ResObject delOperator(OperatorEditParam operatorEditParam) {
                return null;
            }
        };
    }
}

