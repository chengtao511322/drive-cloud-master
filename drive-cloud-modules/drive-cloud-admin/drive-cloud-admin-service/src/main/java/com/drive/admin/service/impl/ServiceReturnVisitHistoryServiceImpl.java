package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.mapper.ServiceReturnVisitHistoryMapper;
import com.drive.admin.pojo.vo.ReturnVisitHistoryEnrollVo;
import com.drive.admin.service.ServiceReturnVisitHistoryService;
import com.drive.common.core.base.BaseService;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客服回访记录 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class ServiceReturnVisitHistoryServiceImpl extends BaseService<ServiceReturnVisitHistoryMapper, ServiceReturnVisitHistoryEntity> implements ServiceReturnVisitHistoryService {

    @Override
    public IPage<ReturnVisitHistoryEnrollVo> pageListReturnVisitHistory(Page page, Wrapper queryWrapper) {
        return this.baseMapper.pageListReturnVisitHistory(page,queryWrapper);
    }
}

