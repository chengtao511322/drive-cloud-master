package com.drive.admin.service.impl;

import com.drive.admin.mapper.ServicePackageDetailMapper;
import com.drive.admin.pojo.dto.ServicePackageDetailPageQueryParam;
import com.drive.admin.pojo.entity.ServicePackageDetailEntity;
import com.drive.admin.service.ServicePackageDetailService;
import com.drive.common.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 服务项目打包明细表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class ServicePackageDetailServiceImpl extends BaseService<ServicePackageDetailMapper, ServicePackageDetailEntity> implements ServicePackageDetailService {


    @Autowired
    private ServicePackageDetailMapper servicePackageDetailMapper;

    @Override
    public Boolean delByCondition(ServicePackageDetailPageQueryParam servicePackageDetailPageQueryParam) {
        return servicePackageDetailMapper.delByCondition(servicePackageDetailPageQueryParam) >0 ?true :false;
    }

    @Override
    public Boolean getPublishStatus(String serviceItemPriceId) {
        return servicePackageDetailMapper.getPublishStatus(serviceItemPriceId) >0 ?true :false;
    }

    @Override
    public int getPublishStatusCount(String serviceItemPriceId) {
        return servicePackageDetailMapper.getPublishStatus(serviceItemPriceId);
    }
}

