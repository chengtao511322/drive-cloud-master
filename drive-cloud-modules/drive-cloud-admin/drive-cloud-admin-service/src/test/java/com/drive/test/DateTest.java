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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        System.out.println(DateUtil.now());
        List<String> arr = new ArrayList();
        List<String> arrJson = new ArrayList();
        arr.add("1");
        arr.add("2");
        arr.add("3");
        arrJson.add("3");
        arrJson.add("4");
        arrJson.add("5");
    /*    arr.stream().forEach((item) ->{
            arr.add("4");
        });*/
       ;
        System.out.println(Stream.concat(arr.stream(), arrJson.stream())
                .distinct()
                .collect(Collectors.toList()));
        /*System.out.println( arr.stream().filter((String student)->student.equals("1")) //筛选出大于150的
                .collect(Collectors.toList()));*/
    }



}
