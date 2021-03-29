package com.drive.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.basics.pojo.entity.ChannelEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 栏目 Mapper 接口
 *
 * @author xiaoguo
 */
public interface ChannelMapper extends BaseMapper<ChannelEntity> {


    /**
     * 数据下一条
     * @param sortNum
     * @return
     */
    ChannelEntity moveDown(@Param("sortNum") Integer sortNum);

    /**
     * 数据 上一条
     * @param sortNum
     * @return
     */

    ChannelEntity moveUp(@Param("sortNum") Integer sortNum);

}

