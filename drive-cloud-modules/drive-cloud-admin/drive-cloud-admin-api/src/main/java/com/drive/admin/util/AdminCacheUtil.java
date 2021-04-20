package com.drive.admin.util;

import cn.hutool.db.nosql.redis.RedisDS;
import com.drive.admin.pojo.vo.AreaVo;
import com.drive.admin.pojo.vo.CoachInfoVo;
import com.drive.admin.pojo.vo.ServiceInfoVo;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.constant.Constants;
import com.drive.common.core.utils.BeanConvertUtils;
import com.drive.common.redis.util.CacheUtils;
import redis.clients.jedis.Jedis;

public class AdminCacheUtil {

    private static Jedis jedis = RedisDS.create().getJedis();

    /**
     * 根据区划编码获取，区划编码值
     * @param baCode  区划编码
     * @return 返回 Entity对象
     */
    public static AreaVo getAreaVo(String baCode){
        return BeanConvertUtils.convertBean(CacheUtils.getCacheKey(Constants.AREA_KEY + baCode), AreaVo.class);
    }

    /**
     * 根据教练id，获取教练Bean对象
     * @param caochId  教练id
     * @return 返回教练Entity对象
     */
    public static CoachInfoVo getCoachInfoVo(String caochId){
        return BeanConvertUtils.convertBean(CacheUtils.getCacheKey(CacheConstants.REDIS_CACHE_COACH_KEY + caochId), CoachInfoVo.class);
    }

    /**
     * 根据客服id，获取客服姓名
     * @param serviceId  客服id
     * @return 返回客服Entity对象
     */
    public static ServiceInfoVo getServiceInfoVo(String serviceId){
        return BeanConvertUtils.convertBean(CacheUtils.getCacheKey(CacheConstants.REDIS_CACHE_SERVICE_KEY + serviceId), ServiceInfoVo.class);
    }


}
