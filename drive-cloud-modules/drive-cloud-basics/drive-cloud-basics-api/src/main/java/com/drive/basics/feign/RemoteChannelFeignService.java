package com.drive.basics.feign;

import com.drive.basics.factory.ChannelFallbackFactory;
import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.vo.ChannelVo;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "RemoteChannelFeignService", value = ServiceNameConstants.BASICS_SERVICE, fallbackFactory = ChannelFallbackFactory.class)
public interface RemoteChannelFeignService {

    @GetMapping("/channel/{id}")
    ResObject<ChannelVo> get(@PathVariable("id") String id);


    @PutMapping("/channel")
    ResObject<ChannelVo> update(@RequestBody ChannelEditParam params);

    @PostMapping("/channel/updateChannel")
    ResObject<ChannelVo> updateChannel(@RequestBody ChannelEditParam params);
}
