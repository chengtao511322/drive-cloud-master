package com.drive.marketing.repository.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.drive.marketing.pojo.dto.RecommendUserPageQueryParam;
import com.drive.marketing.repository.RecommendManagertRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Slf4j
@Service
public class RecommendManagertRepositoryImpl implements RecommendManagertRepository {

    //地址
    private final String URL = "https://www.xuechexwz.com/driverprince-api/v2.3.0/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject getRecommendManagert(String managerId) {
        log.info(this.getClass() + "getRecommendManagert请求参数{}",managerId);
        try {
            HashMap params = new HashMap();
            // 学员ID
            params.put("operatorId","bbdc1bd499b241daa6fe99063e63a193");
            params.put("managerId",managerId);
            String encoded = Base64.encode(JSONObject.toJSONString(params).getBytes());
            MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("data", encoded);
            String result = restTemplate.postForObject(URL + "/app/tRecommendUserAction/selectTRecommendManagerInfo",paramMap,String.class);
            //String result = restTemplate.postForObject("http://125.0.8.224:8080/driverprince-api/app/publicAction/addPayFlow",paramMap,String.class);
            //String base64Res = new String(Base64.getDecoder().decode(result.getBytes()));
            JSONObject jsonResult = (JSONObject) JSONObject.parse(result);
            String decodeStr = Base64.decodeStr(jsonResult.getString("result"));
            // 00000
            JSONObject resultData = (JSONObject) JSONObject.parse(decodeStr);
            log.info("请求古意结果{}",resultData);
            JSONObject jsonObject = null;
            if (resultData.getString("code").equals("00000")){
                jsonObject = (JSONObject) JSONObject.parse(resultData.getString("data"));
            }
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public JSONObject getRecommendUser(String ercommendUserId) {
        log.info(this.getClass() + "pageList请求参数{}",ercommendUserId);
        try {
            HashMap params = new HashMap();
            // 学员ID
            params.put("operatorId","bbdc1bd499b241daa6fe99063e63a193");
            params.put("recommendId",ercommendUserId);
            String encoded = Base64.encode(JSONObject.toJSONString(params).getBytes());
            MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("data", encoded);
            String result = restTemplate.postForObject(URL + "/app/tRecommendUserAction/selectTRecommendUserInfo",paramMap,String.class);
            //String result = restTemplate.postForObject("http://125.0.8.224:8080/driverprince-api/app/publicAction/addPayFlow",paramMap,String.class);
            //String base64Res = new String(Base64.getDecoder().decode(result.getBytes()));
            JSONObject jsonResult = (JSONObject) JSONObject.parse(result);
            String decodeStr = Base64.decodeStr(jsonResult.getString("result"));
            // 00000
            JSONObject resultData = (JSONObject) JSONObject.parse(decodeStr);
            log.info("请求古意结果{}",resultData);
            JSONObject jsonObject = null;
            if (resultData.getString("code").equals("00000")){
                jsonObject = (JSONObject) JSONObject.parse(resultData.getString("data"));
            }
            return jsonObject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONArray recommendUserList(RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "pageList请求参数{}",param);
        try {
            HashMap params = new HashMap();
            // 学员ID
            params.put("operatorId",param.getTenantId());
            params.put("pageSize","1000");
            params.put("managerId",param.getChannelManagerId());
            String encoded = Base64.encode(JSONObject.toJSONString(params).getBytes());
            MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("data", encoded);
            String result = restTemplate.postForObject(URL + "/app/tRecommendUserAction/selectTRecommendUserList",paramMap,String.class);
            //String result = restTemplate.postForObject("http://125.0.8.224:8080/driverprince-api/app/publicAction/addPayFlow",paramMap,String.class);
            //String base64Res = new String(Base64.getDecoder().decode(result.getBytes()));
            JSONObject jsonResult = (JSONObject) JSONObject.parse(result);
            String decodeStr = Base64.decodeStr(jsonResult.getString("result"));
            // 00000
            JSONObject resultData = (JSONObject) JSONObject.parse(decodeStr);
            log.info("请求古意结果{}",resultData);
            JSONArray jsonArray = null;
            if (resultData.getString("code").equals("00000")){
                JSONObject jsonData = (JSONObject) JSONObject.parse(resultData.getString("data"));
                jsonArray = JSONUtil.parseArray(jsonData.getString("dataList"));
            }
            return jsonArray;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
