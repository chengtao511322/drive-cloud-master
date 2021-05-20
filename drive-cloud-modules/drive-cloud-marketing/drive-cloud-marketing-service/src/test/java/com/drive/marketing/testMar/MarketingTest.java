package com.drive.marketing.testMar;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.drive.admin.pojo.vo.CoachInfoVo;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.GsonUtil;
import com.drive.common.core.utils.JsonToMap;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
public class MarketingTest {


    public static void main(String[] args) {
        CoachInfoVo coachInfoVo = null;
        String bodyData = "{sign=OZLClUE2ADUFkVeDL2nd0ctuu8AOvb6WhFG3TZ9vtK3TtezOjV5Om6tOYHJ+wbwvlgt7+xw4TyakqsGFnbhA9g64MfWN99lc2BSUmQgciENgzhqosVvd7ZvBYomE3y3luwgceWVjbPv3hxrDw3sTFTAemDAV8VeXPqK+eBXnrBr7iwVWP//g+X3jOKh9aPPrHOESCOrIgVXCvirkvOHAzV0/L7CpPHxAh9EvDEWIFmrqMuiGY4HWIbCcdxggdLHhWV05gLdOmAgmGKYvyWzW/MvqUIkOoJos2tx3i+DYioycmd/29UYICsoUJJR8np2IP1zL2C2lssPsn5eOYrUAcA==, alipay_trade_refund_response={\"msg\":\"Success\",\"code\":\"10000\",\"out_trade_no\":\"20210520135159890001\",\"refund_fee\":\"0.01\",\"gmt_refund_pay\":\"2021-05-20 13:56:24\",\"send_back_fee\":\"0.00\",\"trade_no\":\"2021052022001435171406623117\",\"buyer_logon_id\":\"185******90\",\"buyer_user_id\":\"2088702799435174\",\"fund_change\":\"Y\"}}";
        //Map data = new Gson().fromJson(bodyData,Map.class);
        Gson gson = new Gson();
        log.info("支付宝退款数据{}", GsonUtil.toMap(bodyData));
       // String sign = data.get("sign");
        //String alipay_trade_refund_response = data.getString("alipay_trade_refund_response");

        //log.info("sign{}",data);
        //coachInfoVo.setId(IdWorker.getIdStr());
        //Optional.ofNullable(coachInfoVo).orElseThrow(()-> new BizException("取指错误"));
        /*Map<StatusEnum,Function<> action> actionMappings = new HashMap<>();
        // 这里泛型 ? 是为方便演示，实际可替换为你需要的类型
        // 初始化
        actionMappings.put(value1, (someParams) -> { doAction1(someParams)});
        actionMappings.put(value2, (someParams) -> { doAction2(someParams)});
        actionMappings.put(value3, (someParams) -> { doAction3(someParams)});
// 省略多余逻辑语句
        actionMappings.get(param).apply(someParams);*/

    }

    private static Map<String, String> parseData(String data){
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        Map<String, String> map = g.fromJson(data, new TypeToken<Map<String, String>>() {}.getType());
        return map;
    }
}
