package com.drive.test;

import cn.hutool.db.nosql.redis.RedisDS;
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
        Jedis jedis = RedisDS.create().getJedis();
       /* long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            jedis.set("basics:"+String.valueOf(i), String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        log.info("the jedis total time is:" + (end - start));*/

        Pipeline pipe = jedis.pipelined(); // 先创建一个 pipeline 的链接对象
        long start_pipe = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            pipe.set("pipe:"+String.valueOf(i), String.valueOf(i));
        }
        pipe.sync(); // 获取所有的 response
        long end_pipe = System.currentTimeMillis();
        log.info("the pipe total time is:" + (end_pipe - start_pipe));

        BlockingQueue<String> logQueue = new LinkedBlockingQueue<String>();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            logQueue.put("i=" + i);
        }
        long stop = System.currentTimeMillis();
        log.info("the BlockingQueue total time is:" + (stop - begin));
    }
}
