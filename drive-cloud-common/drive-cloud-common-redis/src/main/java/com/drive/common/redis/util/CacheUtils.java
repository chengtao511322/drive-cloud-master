package com.drive.common.redis.util;

import com.alibaba.fastjson.JSONObject;
import com.drive.common.core.utils.SpringUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.redis.service.RedisService;

import java.util.List;

/**
 * 字典工具类
 * 
 * @author ruoyi
 */
public class CacheUtils<T>
{
    /**
     * 设置字典缓存
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

    public static JSONObject getDataCache(String key)
    {
        Object cacheObj = SpringUtils.getBean(RedisService.class).getStr(getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            JSONObject dictDatas = StringUtils.cast(cacheObj);
            return dictDatas;
        }
        return null;
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
