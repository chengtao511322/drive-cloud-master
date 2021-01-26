package com.drive.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.dto.ServiceItemPricePageQueryParam;
import com.drive.admin.pojo.entity.ServiceItemPriceEntity;
import com.drive.admin.pojo.vo.ServiceItemPriceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * 服务项目价格表 服务类
 *
 * @author JoyoDuan
 */
public interface ServiceItemPriceService extends IService<ServiceItemPriceEntity>{

    /**
     * 查询服务项目价格列表
     * @param ew
     * @return
     */
    List<ServiceItemPriceVo> queryList(@Param("ew") Wrapper<ServiceItemPricePageQueryParam> ew);

    /**
     * 分页
     * @param page
     * @param ew
     * @return
     */
    IPage<ServiceItemPriceVo> queryPageList(Page page, @Param("ew") Wrapper<ServiceItemPricePageQueryParam> ew);


    /**
     * 通过id 查询服务项
     * @param ew
     * @return
     */
    List<ServiceItemPriceVo> getQueryListById(@Param("ew") Wrapper<ServiceItemPricePageQueryParam> ew);
}

