package com.drive.basics.factory;

import com.drive.basics.feign.RemoteChannelAuthFeignService;
import com.drive.basics.feign.RemoteChannelFeignService;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.vo.ChannelVo;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@Slf4j
public class ChannelAuthFallbackFactory implements FallbackFactory<RemoteChannelAuthFeignService> {

    @Override
    public RemoteChannelAuthFeignService create(Throwable throwable) {
        return new RemoteChannelAuthFeignService() {
            @Override
            public ResObject updateChannelAuth(@Valid ChannelAuthEditParam channelAuthEditParam) {
                return R.failure();
            }

            @Override
            public ResObject copyChannelAuth(ChannelAuthEditParam channelAuthEditParam) {
                return R.failure();
            }
        };

    }
}

