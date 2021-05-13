package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.drive.admin.mapper.CoachingGridMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.pojo.entity.CoachingGridEntity;
import com.drive.admin.service.CoachingGridService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.CacheConstants;
import com.drive.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 平台训练场地表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class CoachingGridServiceImpl extends BaseService<CoachingGridMapper, CoachingGridEntity> implements CoachingGridService {


    @Autowired
    private RedisService redisService;


    // 系统加载初始化数据
    @PostConstruct
    private void initData(){
        QueryWrapper<CoachingGridEntity> wrapper = new QueryWrapper<CoachingGridEntity>();
        List<CoachingGridEntity> coachingGridList = super.list(wrapper);
        long startTime = System.currentTimeMillis();
        Map map = new ConcurrentHashMap(coachingGridList.size());
        coachingGridList.stream().forEach((item) -> {
            // 打印正在执行的缓存线程信息
            map.put(CacheConstants.REDIS_COACHING_GRID_KEY +item.getId(),item);
        });
        redisService.executePipelined(map);
        //cachedThreadPool.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

