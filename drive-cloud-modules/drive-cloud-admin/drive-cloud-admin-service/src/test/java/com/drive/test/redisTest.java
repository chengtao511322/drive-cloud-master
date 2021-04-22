package com.drive.test;

import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.vo.AreaVo;
import com.drive.common.core.utils.ApplicationContextUtil;
import com.drive.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Slf4j
public class redisTest {

    public static void main(String[] args) throws InterruptedException {
        //System.out.println(ApplicationContextUtil.getBean(RedisService.class));;
        String res = "{\"baCode\":\"110101\",\"baName\":\"东城区\",\"baParentId\":\"110100\"}";
        System.out.println(JSONObject.parseObject(res,AreaVo.class));
    }
}
