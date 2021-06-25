package com.drive.marketing.feign;

import com.drive.admin.api.RemoteRecommendUserFeignService;
import com.drive.admin.pojo.vo.RecommendUserVo;
import com.drive.common.core.biz.ResCodeEnum;
import com.drive.common.core.biz.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Component
@Slf4j
public class RecommendUserFeign {

    @Autowired
    private RemoteRecommendUserFeignService remoteRecommendUserFeignService;


    /**
     * 异步获取推广商
     * @param promoteUserId
     * @return
     */
    public Map<String, RecommendUserVo> getBatchRecommendUserVo(String[] promoteUserId){
        Map<String, RecommendUserVo> recommendUserVo = null;
        // 查询推广商
        ResObject<Map<String, RecommendUserVo>> promoteUser = remoteRecommendUserFeignService.batchRecommendUserVo(promoteUserId);
        if (promoteUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && promoteUser.getData() != null){
            recommendUserVo = promoteUser.getData();
        }
        log.info("转化后的运营商数据{}",recommendUserVo);
        return recommendUserVo;
    }
}
