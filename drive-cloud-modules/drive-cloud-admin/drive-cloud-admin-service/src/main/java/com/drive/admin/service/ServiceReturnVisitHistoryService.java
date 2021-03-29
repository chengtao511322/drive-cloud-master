package com.drive.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;
import com.drive.admin.pojo.vo.ReturnVisitHistoryEnrollVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 客服回访记录 服务类
 *
 * @author xiaoguo
 */
public interface ServiceReturnVisitHistoryService extends IService<ServiceReturnVisitHistoryEntity>{

    IPage<ReturnVisitHistoryEnrollVo> pageListReturnVisitHistory(Page page, Wrapper queryWrapper);
}

