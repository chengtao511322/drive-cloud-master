package com.drive.admin.factory;

import com.drive.admin.api.RemoteOneFeeSystemPriceFeignService;
import com.drive.admin.pojo.dto.OneFeeSystemPriceEditParam;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RecommendOneFeeSystemPriceFallbackFactory implements FallbackFactory<RemoteOneFeeSystemPriceFeignService> {
    @Override
    public RemoteOneFeeSystemPriceFeignService create(Throwable throwable) {
        return new RemoteOneFeeSystemPriceFeignService() {
            @Override
            public ResObject get(String id) {
                return R.failure("调用出错");
            }

            @Override
            public ResObject getServicePackageTree(OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam) {
                return R.failure();
            }

        };
    }
}
