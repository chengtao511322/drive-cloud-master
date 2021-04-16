package com.drive.marketing.api;

import com.drive.common.core.constant.ServiceNameConstants;
import com.drive.marketing.factory.ActivityFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户服务
 * @author xiaoguo
 */
@FeignClient(contextId = "RemoteActivityService", value = ServiceNameConstants.MARKETING_SERVICE, fallbackFactory = ActivityFallbackFactory.class)
public interface RemoteActivityService {

}
