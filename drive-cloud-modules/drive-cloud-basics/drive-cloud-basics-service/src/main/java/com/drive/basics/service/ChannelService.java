package com.drive.basics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.basics.pojo.entity.ChannelEntity;

/**
 *
 * 栏目 服务类
 *
 * @author xiaoguo
 */
public interface ChannelService extends IService<ChannelEntity>{

    /**
     * 数据下一条
     * @param sortNum
     * @return
     */
    ChannelEntity moveDown(Integer sortNum);

    /**
     * 数据 上一条
     * @param sortNum
     * @return
     */

    ChannelEntity moveUp(Integer sortNum);
}

