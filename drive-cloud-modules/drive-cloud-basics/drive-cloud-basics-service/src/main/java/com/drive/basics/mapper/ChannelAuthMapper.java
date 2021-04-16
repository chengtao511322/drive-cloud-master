package com.drive.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.basics.pojo.entity.ChannelAuthEntity;

/**
 * 菜单 按钮 用户拥有权限管理 Mapper 接口
 *
 * @author xiaoguo
 */
public interface ChannelAuthMapper extends BaseMapper<ChannelAuthEntity> {

    /**
     * 根据条件删除数据
     * @param channelAuthEditParam
     * @return
     */
    int deleteCondition(ChannelAuthEditParam channelAuthEditParam);

}

