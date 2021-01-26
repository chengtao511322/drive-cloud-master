package com.drive.admin.api;


import com.drive.admin.factory.RecommendManagerFallbackFactory;
import com.drive.admin.pojo.vo.RecommendManagerVo;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 渠道经理用户服务
 * @author DreamChan
 */
@FeignClient(contextId = "RemoteRecommendManagerFeignService", value = ServiceNameConstants.ADMIN_SERVICE,fallbackFactory = RecommendManagerFallbackFactory.class)
public interface RemoteRecommendManagerFeignService {

    /**
     * 通过ID获取数据
     * @param id
     * @return
     */

    @GetMapping(value = "/recommendManager/{id}")
    ResObject<RecommendManagerVo> get(@PathVariable(value = "id") String id);

}
