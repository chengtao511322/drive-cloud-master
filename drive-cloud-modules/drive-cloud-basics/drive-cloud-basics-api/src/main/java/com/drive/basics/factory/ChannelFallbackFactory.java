package com.drive.basics.factory;

import com.drive.basics.feign.RemoteChannelFeignService;
import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.vo.ChannelVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ChannelFallbackFactory implements FallbackFactory<RemoteChannelFeignService> {

    @Override
    public RemoteChannelFeignService create(Throwable throwable) {
        return new RemoteChannelFeignService() {
            @Override
            public ResObject<ChannelVo> get(String id) {
                return R.failure();
            }

            @Override
            public ResObject<ChannelVo> update(ChannelEditParam params) {
                return R.failure();
            }

            @Override
            public ResObject<ChannelVo> updateChannel(ChannelEditParam params) {
                return R.failure();
            }
        };

    }
}

