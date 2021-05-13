package com.drive.admin.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.drive.admin.pojo.vo.*;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.redis.util.CacheUtils;

public class AdminCacheUtil {


    /**
     * 根据区划编码获取，区划编码值
     * @param baCode  区划编码
     * @return 返回 区划编码名称
     */
    public static String getAreaName(String baCode){
        if (StrUtil.isEmpty(baCode)){
            return null;
        }
        // JSONObject str = CacheUtils.getDataJedisCache(CacheConstants.REDIS_CACHE_AREA_KEY  + baCode);
        AreaVo areaVo = JSONObject.parseObject(CacheUtils.getCache(CacheConstants.REDIS_CACHE_AREA_KEY  + baCode),AreaVo.class);
        if(StringUtils.isNull(areaVo)){
            return null;
        }
        return areaVo.getBaName();
    }

    /**
     * 根据区划编码获取，区划编码值
     * @param baCode  区划编码
     * @return 返回 区划编码JSONObject对象
     */
    public static AreaVo getAreaInfo(String baCode){
        if (StrUtil.isEmpty(baCode)){
            return null;
        }
        AreaVo areaVo = JSONObject.parseObject(CacheUtils.getCache(CacheConstants.REDIS_CACHE_AREA_KEY  + baCode),AreaVo.class);
        if(StringUtils.isNull(areaVo)){
            return null;
        }
        return areaVo;
    }



    /**
     * 根据教练id，获取教练Bean对象
     * @param caochId  教练id
     * @return 返回教练JSONObject对象
     */
    public static CoachInfoVo getCoachInfo(String caochId){
        if (StrUtil.isEmpty(caochId)){
            return null;
        }
        CoachInfoVo coachInfoVo = JSONObject.parseObject(CacheUtils.getCache(CacheConstants.REDIS_CACHE_COACH_KEY + caochId),CoachInfoVo.class);
        if(StringUtils.isNull(coachInfoVo)){
            return null;
        }
        return coachInfoVo;
    }

    /**
     * 根据教练id，获取教练Bean对象
     * @param caochId  教练id
     * @return 返回教练教练姓名
     */
    public static String getCoachName(String caochId){
        if (StrUtil.isEmpty(caochId)){
            return null;
        }
        CoachInfoVo coachInfoVo = JSONObject.parseObject(CacheUtils.getCache(CacheConstants.REDIS_CACHE_COACH_KEY + caochId),CoachInfoVo.class);
        if(StringUtils.isNull(coachInfoVo)){
            return null;
        }
        return coachInfoVo.getRealName();
    }


    /**
     * 根据客服id，获取客服姓名
     * @param serviceId  客服id
     * @return 返回客服JSONObject对象
     */
    public static ServiceInfoVo getServiceInfo(String serviceId){
        if (StrUtil.isEmpty(serviceId)){
            return null;
        }
        ServiceInfoVo serviceInfoVo = JSONObject.parseObject(CacheUtils.getCache(CacheConstants.REDIS_CACHE_SERVICE_KEY+serviceId),ServiceInfoVo.class);
        if(StringUtils.isNull(serviceInfoVo)){
            return null;
        }
        return serviceInfoVo;
    }


    /**
     * 根据客服id，获取客服姓名
     * @param serviceId  客服id
     * @return 返回客服姓名
     */
    public static String getServiceRealName(String serviceId){
        if (StrUtil.isEmpty(serviceId)){
            return null;
        }
        ServiceInfoVo serviceInfoVo = JSONObject.parseObject(CacheUtils.getCache(CacheConstants.REDIS_CACHE_SERVICE_KEY+serviceId),ServiceInfoVo.class);
        if(StringUtils.isNull(serviceInfoVo)){
            return null;
        }
        return serviceInfoVo.getRealName();
    }
    /**
     * 根据id，获取版型
     * @param serviceId  id
     * @return 返回版型名称
     */
    public static String getClassName(String classId){
        if (StrUtil.isEmpty(classId)){
            return null;
        }
        OneFeeSystemPriceVo oneFeeSystemPriceVo = JSONObject.parseObject(CacheUtils.getCache(CacheConstants.REDIS_CACHE_CLASS_KEY+classId),OneFeeSystemPriceVo.class);
        if(StringUtils.isNull(oneFeeSystemPriceVo)){
            return null;
        }
        return oneFeeSystemPriceVo.getName();
    }


    /**
     * 场地
     * @param coachingId
     * @return
     */
    public static String getCoachingGridName(String coachingId){
        if (StrUtil.isEmpty(coachingId)){
            return null;
        }
        CoachingGridVo coachingGridVo = JSONObject.parseObject(CacheUtils.getCache(CacheConstants.REDIS_COACHING_GRID_KEY+coachingId),CoachingGridVo.class);
        if(StringUtils.isNull(coachingGridVo)){
            return null;
        }
        return coachingGridVo.getName();
    }
}
