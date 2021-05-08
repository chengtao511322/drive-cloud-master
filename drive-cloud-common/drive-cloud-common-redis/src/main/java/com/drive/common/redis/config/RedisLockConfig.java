package com.drive.common.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.util.concurrent.locks.Lock;

/**
 * 分布式锁实现
   * @author xiaoguo
 * @date 2019-07-30
  */
@Configuration
public class RedisLockConfig {
     @Bean
     public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
         return new RedisLockRegistry(redisConnectionFactory, "asdf");
     }


 /*@Autowired
  private RedisLockRegistry redisLockRegistry;

  public void save(Integer userId) {

     String lockKey = "order:" + userId;

      Lock lock = redisLockRegistry.obtain(lockKey);
      try {
         lock.lock();

        //todo

     } finally {
         lock.unlock();
     }

  }*/
}