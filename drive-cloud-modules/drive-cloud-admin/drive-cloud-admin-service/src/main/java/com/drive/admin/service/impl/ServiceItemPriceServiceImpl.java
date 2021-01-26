package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.mapper.ServiceItemPriceMapper;
import com.drive.admin.pojo.dto.ServiceItemPricePageQueryParam;
import com.drive.admin.pojo.entity.ServiceItemPriceEntity;
import com.drive.admin.pojo.vo.ServiceItemPriceVo;
import com.drive.admin.service.ServiceItemPriceService;
import com.drive.common.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务项目价格表 服务实现类
 *
 * @author JoyoDuan
 */
@Service
public class ServiceItemPriceServiceImpl extends BaseService<ServiceItemPriceMapper, ServiceItemPriceEntity> implements ServiceItemPriceService {


    @Autowired
    private ServiceItemPriceMapper serviceItemPriceMapper;

    @Override
    public List<ServiceItemPriceVo> queryList(Wrapper<ServiceItemPricePageQueryParam> ew) {
        return serviceItemPriceMapper.queryList(ew);
    }

    @Override
    public IPage<ServiceItemPriceVo> queryPageList(Page page, Wrapper<ServiceItemPricePageQueryParam> ew) {
        return serviceItemPriceMapper.queryList(page,ew);
    }

    @Override
    public List<ServiceItemPriceVo> getQueryListById(Wrapper<ServiceItemPricePageQueryParam> ew) {
        return serviceItemPriceMapper.getQueryListById(ew);
    }
}

