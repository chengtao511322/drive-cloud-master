package com.drive.admin.service.impl;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.mapper.CoachInfoMapper;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.service.CoachInfoService;
import com.drive.common.core.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 教练信息表 服务实现类
 *
 * @author xiaoguo
 */
@Service
@Slf4j
public class CoachInfoServiceImpl extends BaseService<CoachInfoMapper, CoachInfoEntity> implements CoachInfoService {

    @Autowired
    private RedisTemplate redisTemplate;

    private  final Jedis jedis = RedisDS.create().getJedis();

    // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
    ExecutorService cachedThreadPool = Executors.newFixedThreadPool(20);


    @PostConstruct
    public void init() {
        QueryWrapper coachQueryWrapper = new QueryWrapper();
        coachQueryWrapper.eq("status", StatusEnum.NORMAL.getCode());
        List<CoachInfoEntity> coachInfoList = this.getBaseMapper().selectList(coachQueryWrapper);
        log.info(this.getClass() + "数据{}", coachInfoList);
        if (coachInfoList.size() <= 0){
            return;
        }

        // redisTemplate.opsForValue().set("viewList:", viewList);
        long startTime = System.currentTimeMillis();
        coachInfoList.stream().forEach((item) -> {
            //for (int i = 0; i < viewList.size(); i++) {
            //pipe.set("pipe:"+viewList.get(0).getCode(), viewList.get(0).getValue());
            // sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
            //Thread.sleep(1000);
                    // 打印正在执行的缓存线程信息
                    //pipeline.sadd("viewList:"+item.getCode(), item.getValue());
                    jedis.set("redisCache:coachItem:coach_"+item.getId(), JSONObject.toJSONString(item));
        });
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

