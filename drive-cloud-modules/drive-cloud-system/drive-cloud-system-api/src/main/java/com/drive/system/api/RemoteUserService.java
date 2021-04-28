package com.drive.system.api;

import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import com.drive.system.factory.UserFallbackFactory;
import com.drive.system.pojo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 * @author DreamChan
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = UserFallbackFactory.class)
public interface RemoteUserService {

    @GetMapping(value = "/user/info/{username}")
    ResObject<UserInfo> info(@PathVariable("username") String username);

}
