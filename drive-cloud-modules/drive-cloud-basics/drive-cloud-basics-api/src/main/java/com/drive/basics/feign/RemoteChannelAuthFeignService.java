package com.drive.basics.feign;

import com.drive.basics.factory.ChannelAuthFallbackFactory;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(contextId = "RemoteChannelAuthFeignService", value = ServiceNameConstants.BASICS_SERVICE, fallbackFactory = ChannelAuthFallbackFactory.class)
public interface RemoteChannelAuthFeignService {


    /**
     * 修改权限菜单
     * @param channelAuthEditParam
     * @return
     */
    @PutMapping("/channelAuth/updateChannelAuth")
    ResObject updateChannelAuth(@Valid @RequestBody ChannelAuthEditParam channelAuthEditParam);

    /**
     * 复制用户权限
     * @param channelAuthEditParam
     * @return
     */
    @PostMapping("/channelAuth/copyChannelAuth")
    ResObject copyChannelAuth(@RequestBody ChannelAuthEditParam channelAuthEditParam);
}
