package com.drive.marketing.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.marketing.mapper.ChannelManagerActivityMapper;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.pojo.vo.ChannelManagerActivityVo;
import com.drive.marketing.service.ChannelManagerActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 渠道经理 可推广表配置 服务实现类
 *
 * @author xiaoguo
 */
@Service
@Slf4j
public class ChannelManagerActivityServiceImpl extends ServiceImpl<ChannelManagerActivityMapper, ChannelManagerActivityEntity> implements ChannelManagerActivityService {


    private final String URL = "http://125.0.8.104/driverprince-api";


    @Autowired
    private ChannelManagerActivityMapper channelManagerActivityMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public IPage<ChannelManagerActivityVo> findPage(Page page, Wrapper<ChannelManagerActivityPageQueryParam> queryWrapper) {
        return channelManagerActivityMapper.findPage(page,queryWrapper);
    }

  /*  @PostConstruct
    public void init()
    {
        HashMap params = new HashMap();
        // 学员ID
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("operatorId", "bbdc1bd499b241daa6fe99063e63a193");
        paramMap.add("driveType", "1");
        String result = restTemplate.postForObject(URL + "/app/oneFeeSystem/selectOneFeeSystemList",paramMap,String.class);
        //String result = restTemplate.postForObject("http://125.0.8.224:8080/driverprince-api/app/publicAction/addPayFlow",paramMap,String.class);
        //String base64Res = new String(Base64.getDecoder().decode(result.getBytes()));
        JSONObject jsonResult = (JSONObject) JSONObject.parse(result);
        // 00000
        log.info("请求古意结果{}",jsonResult);
        JSONArray jsonArray = null;
        if (jsonResult.getString("code").equals("00000")){
            JSONObject jsonData = (JSONObject) JSONObject.parse(jsonResult.getString("data"));
            jsonArray = JSONUtil.parseArray(jsonData.getString("dataList"));
        }
       *//* for (OperatorEntity operator : operatorEntityList)
        {
            //JSONObject jsonObject = BeanConvertUtils.convertBean(operator, JSONObject.class);
            redisService.set(getCacheKey(operator.getId()), operator);
        }*//*
    }*/
}

