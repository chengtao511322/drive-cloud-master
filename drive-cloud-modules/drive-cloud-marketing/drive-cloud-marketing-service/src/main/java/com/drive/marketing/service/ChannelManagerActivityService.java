package com.drive.marketing.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.pojo.vo.ChannelManagerActivityVo;
import org.apache.ibatis.annotations.Param;

/**
 *
 * 渠道经理 可推广表配置 服务类
 *
 * @author xiaoguo
 */
public interface ChannelManagerActivityService extends IService<ChannelManagerActivityEntity>{

    /**
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<ChannelManagerActivityVo>  findPage(Page page, @Param("ew") Wrapper<ChannelManagerActivityPageQueryParam> queryWrapper);
}

