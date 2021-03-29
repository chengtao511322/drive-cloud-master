package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.entity.ServiceReturnVisitHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.admin.pojo.vo.ReturnVisitHistoryEnrollVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 客服回访记录 Mapper 接口
 *
 * @author xiaoguo
 */
public interface ServiceReturnVisitHistoryMapper extends BaseMapper<ServiceReturnVisitHistoryEntity> {

    /**
     * 查询回访记录
     * @param queryWrapper
     * @return
     */
    IPage<ReturnVisitHistoryEnrollVo> pageListReturnVisitHistory(Page page, @Param("ew") Wrapper queryWrapper);
}

