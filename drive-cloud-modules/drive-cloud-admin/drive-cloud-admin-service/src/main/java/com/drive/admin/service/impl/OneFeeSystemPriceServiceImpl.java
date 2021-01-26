package com.drive.admin.service.impl;

import com.drive.admin.mapper.OneFeeSystemPriceMapper;
import com.drive.admin.pojo.dto.TreeNodeCategoryDto;
import com.drive.admin.pojo.entity.OneFeeSystemPriceEntity;
import com.drive.admin.service.OneFeeSystemPriceService;
import com.drive.common.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学车一费制定价表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class OneFeeSystemPriceServiceImpl extends BaseService<OneFeeSystemPriceMapper, OneFeeSystemPriceEntity> implements OneFeeSystemPriceService {


    @Autowired
    private OneFeeSystemPriceMapper oneFeeSystemPriceMapper;

    @Override
    public List<TreeNodeCategoryDto> getServicePackageTree(String tenantId) {
        return oneFeeSystemPriceMapper.getServicePackageTree(tenantId);
    }
}

