package com.drive.admin.util;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.drive.admin.pojo.vo.AreaVo;
import com.drive.admin.pojo.vo.CoachInfoVo;
import com.drive.admin.pojo.vo.ServiceInfoVo;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.redis.util.CacheUtils;
import redis.clients.jedis.Jedis;

public class AdminCacheUtil {

    private static Jedis jedis = RedisDS.create().getJedis();

    /**
     * 根据区划编码获取，区划编码值
     * @param baCode  区划编码
     * @return 返回 区划编码名称
     */
    public static String getAreaName(String baCode){
        JSONObject str = CacheUtils.getDataCache(Constants.AREA_KEY + baCode);
        if(StringUtils.isNotNull(str)){
            return str.getString("baName");
        }
        return "";
    }

    /**
     * 根据区划编码获取，区划编码值
     * @param baCode  区划编码
     * @return 返回 区划编码JSONObject对象
     */
    public static JSONObject getAreaInfo(String baCode){
        return CacheUtils.getDataCache(Constants.AREA_KEY + baCode);
    }



    /**
     * 根据教练id，获取教练Bean对象
     * @param caochId  教练id
     * @return 返回教练JSONObject对象
     */
    public static JSONObject getCoachInfo(String caochId){
        return CacheUtils.getDataCache(CacheConstants.REDIS_CACHE_COACH_KEY + caochId);
    }

    /**
     * 根据教练id，获取教练Bean对象
     * @param caochId  教练id
     * @return 返回教练教练姓名
     */
    public static String getCoachName(String caochId){
        JSONObject str = CacheUtils.getDataCache(CacheConstants.REDIS_CACHE_COACH_KEY + caochId);
        if(StringUtils.isNotNull(str)){
            return str.getString("realName");
        }
        return "";
    }


    /**
     * 根据客服id，获取客服姓名
     * @param serviceId  客服id
     * @return 返回客服JSONObject对象
     */
    public static JSONObject getServiceInfo(String serviceId){
        return CacheUtils.getDataCache(CacheConstants.REDIS_CACHE_SERVICE_KEY + serviceId);
    }


    /**
     * 根据客服id，获取客服姓名
     * @param serviceId  客服id
     * @return 返回客服姓名
     */
    public static String getServiceRealName(String serviceId){
        JSONObject str =  CacheUtils.getDataCache(CacheConstants.REDIS_CACHE_SERVICE_KEY + serviceId);
        if(StringUtils.isNotNull(str)){
            return str.getString("realName");
        }
        return "";
    }
}
