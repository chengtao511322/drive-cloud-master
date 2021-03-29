package com.drive.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drive.admin.mapper.AreaMapper;
import com.drive.admin.pojo.entity.AreaEntity;
import com.drive.admin.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

