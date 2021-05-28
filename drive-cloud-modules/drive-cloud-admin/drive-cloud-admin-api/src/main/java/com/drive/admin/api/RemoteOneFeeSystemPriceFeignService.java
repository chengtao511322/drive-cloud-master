package com.drive.admin.api;

import com.drive.admin.factory.RecommendOneFeeSystemPriceFallbackFactory;
import com.drive.admin.pojo.dto.OneFeeSystemPriceEditParam;
import com.drive.admin.pojo.dto.TreeNodeCategoryDto;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 班型服务
 */
@FeignClient(contextId = "RemoteOneFeeSystemPriceFeignService", value = ServiceNameConstants.ADMIN_SERVICE,fallbackFactory = RecommendOneFeeSystemPriceFallbackFactory.class)
public interface RemoteOneFeeSystemPriceFeignService {


    @GetMapping("/oneFeeSystemPrice/{id}")
    ResObject get(@PathVariable(value = "id") String id);
    @PostMapping("/oneFeeSystemPrice/getServicePackageTree")
    ResObject<List<TreeNodeCategoryDto>> getServicePackageTree(@RequestBody OneFeeSystemPriceEditParam oneFeeSystemPriceEditParam);
}
