package com.drive.admin.api;


import com.drive.admin.factory.RecommendUserFallbackFactory;
import com.drive.admin.pojo.dto.RecommendUserPageQueryParam;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.admin.pojo.vo.StudentInfoVo;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 推广商用户服务
 * @author DreamChan
 */
@FeignClient(contextId = "RemoteRecommendUserFeignService", value = ServiceNameConstants.ADMIN_SERVICE,fallbackFactory = RecommendUserFallbackFactory.class)
public interface RemoteRecommendUserFeignService {

    /**
     * 通过ID获取数据
     * @param id
     * @return
     */
    @GetMapping(value = "/recommendUser/{id}")
    ResObject<RecommendUserVo> get(@PathVariable(value = "id") String id);

    /**
     * 批量获取数据
     * @param id
     * @return
     */
    @GetMapping(value = "/recommendUser/batchRecommendUserVo/{ids}")
    ResObject<Map<String, RecommendUserVo>> batchRecommendUserVo(@PathVariable(value = "ids") String[] ids);


    /**
     *  推广人员信息表列表
     * @param channelManagerId
     * @return
     */
    // @ApiOperation("推广人员信息表列表")
    @GetMapping(value = "/recommendUser/getRecommendUserByChannelManagerId/{channelManagerId}")
    ResObject<List<RecommendUserVo>> getRecommendUserByChannelManagerId(@PathVariable(value = "channelManagerId") String channelManagerId);

    /**
     *  根据手机号获取推广人员信息
     */
    @PostMapping(value = "/recommendUser/getRecommendUser/{phone}")
    ResObject<RecommendUserVo> getRecommendUserByPhone(@PathVariable(value = "phone") String phone);

}
