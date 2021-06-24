package com.drive.common.redis.util;

import com.alibaba.fastjson.JSONObject;
import com.drive.common.core.utils.SpringContextUtil;
import com.drive.common.core.utils.SpringUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 字典工具类
 * 
 * @author xiagouo
 */
@Slf4j
public class CacheUtils<T>
{

    private static         RedisService redisService = SpringContextUtil.getBean(RedisService.class);
  /* 设置字典缓存
     * 
     * @param key 参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<JSONObject> dictDatas)
    {
        SpringUtils.getBean(RedisService.class).set(getCacheKey(key), dictDatas);
    }

    public static void setDataCache(String key, JSONObject data)
    {
        SpringUtils.getBean(RedisService.class).set(getCacheKey(key), data);
    }

    public static Object getDataCache(String key)
    {
        Object cacheObj = SpringContextUtil.getBean(RedisService.class).getStr(getCacheKey(key));
        return cacheObj;
    }

    /***
     * redis
     * @param key
     * @return
     */
    public static String getCache(String key)
    {
        String str =redisService.getStr(key);
        return str;
    }
    public static JSONObject getDataJedisCache(String key)
    {
        JSONObject cacheObj= null;
        log.debug("区域{}",cacheObj);
/*        if (StringUtils.isNotNull(cacheObj))
        {
            JSONObject dictDatas = StringUtils.cast(cacheObj);
        }*/
        return cacheObj;
    }

    /**
     * 获取字典缓存
     * 
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<JSONObject> getDictCache(String key)
    {
        Object cacheObj = SpringUtils.getBean(RedisService.class).getStr(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            List<JSONObject> dictDatas = StringUtils.cast(cacheObj);
            return dictDatas;
        }
        return null;
    }

    /**
     * 清空字典缓存
     */
   /* public static void clearDictCache()
    {
        Collection<String> keys = SpringUtils.getBean(RedisService.class).keys(Constants.SYS_DICT_KEY + "*");
        SpringUtils.getBean(RedisService.class).deleteObject(keys);
    }*/

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return  configKey;
    }
}
