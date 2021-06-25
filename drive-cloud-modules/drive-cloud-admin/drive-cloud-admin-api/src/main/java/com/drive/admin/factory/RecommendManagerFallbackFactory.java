
package com.drive.admin.factory;

import com.drive.admin.api.RemoteRecommendManagerFeignService;
import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.admin.pojo.vo.RecommendUserVo;
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
public class RecommendManagerFallbackFactory implements FallbackFactory<RemoteRecommendManagerFeignService> {


    @Override
    public RemoteRecommendManagerFeignService create(Throwable throwable) {
        return new RemoteRecommendManagerFeignService() {
            @Override
            public ResObject<RecommendManagerVo> get(String id) {
                log.error("调用接口出错");
                return R.failure();
            }

            @Override
            public ResObject<Map<String, RecommendManagerVo>> batchRecommendManager(String[] ids) {
                return R.failure();
            }
        };
    }
}

