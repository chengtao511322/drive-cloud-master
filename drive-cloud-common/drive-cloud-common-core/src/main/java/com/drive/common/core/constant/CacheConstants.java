package com.drive.common.core.constant;

/**
 * 缓存的key 常量
 * 
 * @author xiaoguo
 */
public interface CacheConstants {
    /**
     * oauth 缓存前缀
     */
    String OAUTH_ACCESS = "oauth:access:";

    /**
     * oauth 客户端信息
     */
    String CLIENT_DETAILS_KEY = "oauth:client:details";
    // 班型
    String REDIS_CACHE_CLASS_KEY = "redisCache::classItem:class_";
    // 教练
    String REDIS_CACHE_COACH_KEY = "redisCache::coachItem:coach_";
    // 区划表
    String REDIS_CACHE_AREA_KEY = "redisCache::areaItem:area_";
    // 客服信息
    String REDIS_CACHE_SERVICE_KEY = "redisCache::serviceItem:service_";

}
