package com.drive.marketing.feign;

import com.drive.admin.api.RemoteRecommendManagerFeignService;
import com.drive.admin.pojo.vo.RecommendManagerVo;
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
public class RecommendManagerFeign {

    @Autowired
    private RemoteRecommendManagerFeignService remoteRecommendManagerFeignService;


    /**
     * 获取批量渠道经理
     * @param managerId
     * @return
     */
    public Map<String, RecommendManagerVo> getBatchRecommendManagerVo(String[] managerId){
        Map<String, RecommendManagerVo> recommendManagerVoMap = null;
        // 查询推广商
        ResObject<Map<String, RecommendManagerVo>> managerUser = remoteRecommendManagerFeignService.batchRecommendManager(managerId);
        if (managerUser.getCode().equals(ResCodeEnum.SUCCESS.getCode()) && managerUser.getData() != null){
            recommendManagerVoMap = managerUser.getData();
        }
        log.info("转化后的运营商数据{}",recommendManagerVoMap);
        return recommendManagerVoMap;
    }
}
