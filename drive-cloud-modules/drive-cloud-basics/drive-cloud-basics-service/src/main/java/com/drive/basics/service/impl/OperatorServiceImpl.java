package com.drive.basics.service.impl;

import com.drive.basics.mapper.OperatorMapper;
import com.drive.basics.pojo.entity.OperatorEntity;
import com.drive.basics.pojo.vo.OperatorItemVo;
import com.drive.basics.service.OperatorService;
import com.drive.common.core.base.BaseService;
import com.drive.common.core.constant.Constants;
import com.drive.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 运营商基础信息 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class OperatorServiceImpl extends BaseService<OperatorMapper, OperatorEntity> implements OperatorService {

    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private RedisService redisService;

   /* @PostConstruct
    public void init()
    {
        Jedis jedis = RedisDS.create().getJedis();
        QueryWrapper<OperatorEntity> wrapper = new QueryWrapper<OperatorEntity>();
        List<OperatorEntity> operatorEntityList = operatorMapper.selectList(wrapper);
        for (OperatorEntity operator : operatorEntityList)
        {
            //JSONObject jsonObject = BeanConvertUtils.convertBean(operator, JSONObject.class);
            jedis.set(getCacheKey(operator.getId()), JSONObject.toJSONString(operator));
        }
    }*/
    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey)
    {
        return Constants.OPERATOR_DEPT_KEY + configKey;
    }


    @CacheEvict(value = "redisCache", key = "'operator:'+ #entity.getId()")
    @Override
    public boolean save(OperatorEntity entity) {
        return super.save(entity);
    }

    @CacheEvict(value = "redisCache", key = "'operator:'+ #entity.getId()")
    @Override
    public boolean updateById(OperatorEntity entity) {
        return super.updateById(entity);
    }

    @CacheEvict(value = "redisCache", key = "'operator:'+ #entity.getId()")
    @Override
    public boolean removeByIds(OperatorEntity entity, Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Cacheable(value = "redisCache", key = "'operator:'+ #id")
    @Override
    public List<OperatorEntity> getOperatorByOperatorId(String operatorId) {
        return this.lambdaQuery().eq(OperatorEntity::getId, operatorId).list();
    }

    @Cacheable(value = "redisCache", key = "'operator:'+ #id")
    @Override
    public List<OperatorItemVo> findAllList() {
        return operatorMapper.findAllList();
    }
}

