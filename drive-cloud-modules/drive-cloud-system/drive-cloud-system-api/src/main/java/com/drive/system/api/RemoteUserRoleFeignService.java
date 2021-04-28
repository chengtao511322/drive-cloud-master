package com.drive.system.api;

import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import com.drive.system.factory.UserRoleFallbackFactory;
import com.drive.system.pojo.vo.RoleDeptVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务
 * @author xiaoguo
 */
@FeignClient(contextId = "RemoteUserRoleFeignService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = UserRoleFallbackFactory.class)
public interface RemoteUserRoleFeignService {

    @GetMapping("/dept/getDeptByRoleId/{roleId}")
    ResObject<RoleDeptVo> getDeptByRoleId(@PathVariable Long roleId);

}
