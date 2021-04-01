package com.drive.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.admin.mapper.AreaMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.service.AreaService;
import com.drive.common.core.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *  服务实现类
 *
 * @author xiaoguo
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, AreaEntity> implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public AreaEntity getByBaCode(String baCode) {
        if (StrUtil.isEmpty(baCode)){
            return null;
        }
        QueryWrapper<AreaEntity> queryWrapper = new QueryWrapper<AreaEntity>();
        queryWrapper.eq("ba_code",baCode);
        return areaMapper.selectOne(queryWrapper);
    }


   /* @PostConstruct
    public void init()
    {
        Jedis jedis = RedisDS.create().getJedis();
        QueryWrapper<AreaEntity> wrapper = new QueryWrapper<AreaEntity>();
        List<AreaEntity> operatorEntityList = areaMapper.selectList(wrapper);
        for (AreaEntity operator : operatorEntityList)
        {
            //JSONObject jsonObject = BeanConvertUtils.convertBean(operator, JSONObject.class);
            jedis.set(getCacheKey(operator.getBaCode()), JSONObject.toJSONString(operator));
        }
    }*/

    private String getCacheKey(String configKey)
    {
        return Constants.AREA_KEY + configKey;
    }
}

