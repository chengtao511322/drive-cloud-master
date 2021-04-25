package com.drive.admin.service.impl;

import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.enums.StatusEnum;
import com.drive.admin.mapper.CoachInfoMapper;
import com.drive.admin.pojo.entity.CoachInfoEntity;
import com.drive.admin.service.CoachInfoService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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




    @Resource
    private RedisService redisService;

    @CacheEvict(value = "redisCache", key = "'coachItem:coach_'+#entity.getId()")
    @Override
    public boolean save(CoachInfoEntity entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "redisCache", key = "'coachItem:coach_'+#entity.getId()")
    @Override
    public boolean updateById(CoachInfoEntity entity) {
        return super.updateById(entity);
    }

    //@Cacheable(value = "redisCache", key = "'coachItem:coach_'+ #id")
    @Override
    public CoachInfoEntity getById(Serializable id) {
        return super.getById(id);
    }

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
        Map map = new ConcurrentHashMap(coachInfoList.size());
        coachInfoList.stream().forEach((item) -> {
                    //jedis.set("redisCache:coachItem:coach_"+item.getId(), JSONObject.toJSONString(item));
            map.put(CacheConstants.REDIS_CACHE_COACH_KEY +item.getId(),JSONObject.toJSONString(item));
        });
        // 提交
        redisService.executePipelined(map);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

