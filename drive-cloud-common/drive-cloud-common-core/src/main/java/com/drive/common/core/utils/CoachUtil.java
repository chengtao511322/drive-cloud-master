package com.drive.common.core.utils;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.constant.Constants;
import redis.clients.jedis.Jedis;

public class CoachUtil {

    private static Jedis jedis = RedisDS.create().getJedis();

    /**
     * 根据区划编码获取，区划编码值
     * @param key  区划编码
     * @return 返回区划编码 Bean 对象
     */
    public static String getAreaCoach(String key){
        return jedis.get(Constants.AREA_KEY + key);
    }


    /**
     * 根据教练id，获取教练姓名
     * @param caochId  教练id
     * @return 返回教练姓名
     */
    public static String getCoachName(String caochId){
        if(StringUtils.isNotNull(caochId)){
            String cacheRes = jedis.get(CacheConstants.REDIS_CACHE_COACH_KEY + caochId);
            JSONObject jsonObject = (JSONObject) JSONObject.parse(cacheRes);
            if (jsonObject != null)
                return jsonObject.getString("realName");
        }
        return "";
    }


    /**
     * 根据客服id，获取客服姓名
     * @param serviceId  客服id
     * @return 返回客服姓名
     */
    public static String getServiceName(String serviceId){
        if(StringUtils.isNotNull(serviceId)){
            String cacheRes = jedis.get(CacheConstants.REDIS_CACHE_SERVICE_KEY + serviceId);
            JSONObject jsonObject = (JSONObject) JSONObject.parse(cacheRes);
            if (jsonObject != null)
                return jsonObject.getString("realName");
        }
        return "";
    }

}
