package com.drive.marketing.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.marketing.pojo.dto.RecommendManagerPageQueryParam;
import com.drive.marketing.pojo.dto.RecommendUserPageQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;

@Api(tags = "推广人员管理")
@Slf4j
@RestController
@RequestMapping("/recommend")
public class RecommendUserController {

    private final String URL = "https://www.xuechexwz.com/driverprince-api/v2.3.0/";

    @Autowired
    @Qualifier("restUrlTemplate")
    private RestTemplate restTemplate;

    @ApiOperation("活动分页列表")
    @GetMapping(value = "/pageList")
    public ResObject pageList(@Valid RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "pageList请求参数{}",param);
        try {
            HashMap params = new HashMap();
            // 学员ID
            params.put("operatorId",param.getTenantId());
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
            return R.success(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @ApiOperation("活动分页列表")
    @GetMapping(value = "/getRecommendUser")
    public ResObject getRecommendUser(@Valid RecommendUserPageQueryParam param) {
        log.info(this.getClass() + "pageList请求参数{}",param);
        try {
            HashMap params = new HashMap();
            // 学员ID
            params.put("operatorId","bbdc1bd499b241daa6fe99063e63a193");
            params.put("recommendId",param.getRecommendId());
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
            JSONArray jsonArray = null;
            if (resultData.getString("code").equals("00000")){
                JSONObject jsonData = (JSONObject) JSONObject.parse(resultData.getString("data"));
                jsonArray = JSONUtil.parseArray(jsonData.getString("dataList"));
            }
            return R.success(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @ApiOperation("活动分页列表")
    @GetMapping(value = "/recommendManagertpageList")
    public ResObject recommendManagertpageList(@Valid RecommendManagerPageQueryParam param) {
        log.info(this.getClass() + "pageList请求参数{}",param);
        try {
            HashMap params = new HashMap();
            // 学员ID
            params.put("operatorId",param.getTenantId());
            params.put("startRow",param.getPageNum());
            params.put("pageSize",param.getPageSize());
            String encoded = Base64.encode(JSONObject.toJSONString(params).getBytes());
            MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
            paramMap.add("data", encoded);
            String result = restTemplate.postForObject(URL + "/app/tRecommendUserAction/selectRecommendManagerList",paramMap,String.class);
            //String result = restTemplate.postForObject("http://125.0.8.224:8080/driverprince-api/app/publicAction/addPayFlow",paramMap,String.class);
            //String base64Res = new String(Base64.getDecoder().decode(result.getBytes()));
            JSONObject jsonResult = (JSONObject) JSONObject.parse(result);
            String decodeStr = Base64.decodeStr(jsonResult.getString("result"));
            // 00000
            JSONObject resultData = (JSONObject) JSONObject.parse(decodeStr);
            log.info("请求古意结果{}",resultData);
            JSONArray jsonArray = null;
            String total = "0";
            if (resultData.getString("code").equals("00000")){
                JSONObject jsonData = (JSONObject) JSONObject.parse(resultData.getString("data"));
                total = jsonData.getString("dataCount");
                jsonArray = JSONUtil.parseArray(jsonData.getString("dataList"));
            }
            return R.success(jsonArray,Long.parseLong(total));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation("活动分页列表")
    @GetMapping(value = "/getRecommendManagert")
    public ResObject getRecommendManagert(@Valid RecommendManagerPageQueryParam param) {
        log.info(this.getClass() + "pageList请求参数{}",param);
        try {
            HashMap params = new HashMap();
            // 学员ID
            params.put("operatorId",param.getTenantId());
            params.put("managerId",param.getManagerId());
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
            JSONArray jsonArray = null;
            if (resultData.getString("code").equals("00000")){
                JSONObject jsonData = (JSONObject) JSONObject.parse(resultData.getString("data"));
                jsonArray = JSONUtil.parseArray(jsonData.getString("dataList"));
            }
            return R.success(jsonArray);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
