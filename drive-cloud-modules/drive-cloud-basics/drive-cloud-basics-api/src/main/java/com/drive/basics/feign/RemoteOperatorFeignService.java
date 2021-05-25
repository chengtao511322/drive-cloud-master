package com.drive.basics.feign;

import com.drive.basics.factory.OperatorFallbackFactory;
import com.drive.basics.pojo.dto.OperatorEditParam;
import com.drive.basics.pojo.vo.OperatorVo;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(contextId = "RemoteOperatorFeignService", value = ServiceNameConstants.BASICS_SERVICE, fallbackFactory = OperatorFallbackFactory.class)
public interface RemoteOperatorFeignService {

    /**
     * 获取运营商基础信息
     */
    @GetMapping(value = "/operator/{id}")
    ResObject<OperatorVo> get(@PathVariable("id") String id);

    // 添加数据
    @PostMapping(value = "/operator/saveOperator")
    ResObject saveOperator(@RequestBody OperatorEditParam operatorEditParam);


    // 修改数据
    @PostMapping(value = "/operator/updateOperator")
    ResObject updateOperator(@RequestBody OperatorEditParam operatorEditParam);


    // 删除数据
    @PostMapping(value = "/operator/delOperator")
    ResObject delOperator(@RequestBody OperatorEditParam operatorEditParam);
}
