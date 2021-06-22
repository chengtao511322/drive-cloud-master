
package com.drive.admin.factory;

import com.drive.admin.api.RemoteRecommendUserFeignService;
import com.drive.admin.pojo.vo.RecommendUserVo;
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
public class RecommendUserFallbackFactory implements FallbackFactory<RemoteRecommendUserFeignService> {


    @Override
    public RemoteRecommendUserFeignService create(Throwable throwable) {
        return new RemoteRecommendUserFeignService() {
            @Override
            public ResObject<RecommendUserVo> get(String id) {
                return R.failure();
            }


            @Override
            public ResObject<List<RecommendUserVo>> getRecommendUserByChannelManagerId(String channelManagerId) {
                return R.failure("服务降级，服务熔断");
            }
        };
    }
}

