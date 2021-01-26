package com.drive.marketing.api;

import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import com.drive.marketing.factory.ActivityFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户服务
 * @author DreamChan
 */
@FeignClient(contextId = "remoteActivityService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = ActivityFallbackFactory.class)
public interface RemoteActivityService {

    @GetMapping(value = "/activity/pageList")
    ResObject pageList();

}
