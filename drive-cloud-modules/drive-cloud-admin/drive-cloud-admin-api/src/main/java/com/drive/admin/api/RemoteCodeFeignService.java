package com.drive.admin.api;


import com.drive.admin.factory.CodeFallbackFactory;
import com.drive.admin.factory.StudentFallbackFactory;
import com.drive.admin.pojo.dto.CodePageQueryParam;
import com.drive.admin.pojo.vo.CodeVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户服务
 * @author DreamChan
 */
@FeignClient(contextId = "RemoteCodeFeignService", value = ServiceNameConstants.ADMIN_SERVICE,fallbackFactory = CodeFallbackFactory.class)
public interface RemoteCodeFeignService {

    /**
     * 通过ID获取数据
     * @param
     * @return
     */

    @PostMapping(value = "/code/findList")
    ResObject<List<CodeVo>> findList(@RequestBody CodePageQueryParam param);

}
