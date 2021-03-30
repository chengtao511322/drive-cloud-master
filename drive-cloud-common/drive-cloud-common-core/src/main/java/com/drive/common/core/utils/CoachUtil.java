package com.drive.common.core.utils;

import cn.hutool.db.nosql.redis.RedisDS;
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


}
