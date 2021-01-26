package com.drive.marketing.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.marketing.pojo.dto.ChannelManagerActivityPageQueryParam;
import com.drive.marketing.pojo.entity.ChannelManagerActivityEntity;
import com.drive.marketing.pojo.vo.ChannelManagerActivityVo;
import org.apache.ibatis.annotations.Param;

/**
 * 渠道经理 可推广表配置 Mapper 接口
 *
 * @author xiaoguo
 */
public interface ChannelManagerActivityMapper extends BaseMapper<ChannelManagerActivityEntity> {

    /**
     * 查询分页
     * @param page
     * @param ew
     * @return
     */
    IPage<ChannelManagerActivityVo> findPage(Page page, @Param("ew") Wrapper<ChannelManagerActivityPageQueryParam> ew);
}

