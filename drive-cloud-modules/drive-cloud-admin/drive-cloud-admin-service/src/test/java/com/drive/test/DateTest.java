package com.drive.test;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        DateTime dateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, 5);
        System.out.println(dateTime);
        //新建FIFOCache
        Cache<String,String> fifoCache = CacheUtil.newFIFOCache(3);
        fifoCache.put("name","xiaoguo");
        fifoCache.get("name");
        Jedis jedis = RedisDS.create().getJedis();
        //jedis.set("name","xiaoguo");
        String result =  jedis.get("operator_dept:01b13fad909245abb042d5efc37b82ee");
        JSON json = JSONUtil.parse(result);
        System.out.println(json.getByPath("name"));
    }
}
