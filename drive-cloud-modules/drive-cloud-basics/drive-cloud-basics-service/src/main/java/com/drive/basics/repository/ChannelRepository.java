package com.drive.basics.repository;

import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.dto.ChannelPageQueryParam;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 栏目 服务类
 *
 * @author xiaoguo
 */
public interface ChannelRepository extends BaseRepository<ChannelPageQueryParam, ChannelEditParam>{

    /**
     * 查询所有
     */
    ResObject allList(ChannelEditParam channelEditParam);

    /**
     * 修改
     * @param channelEditParam
     * @return
     */
    ResObject updateChannel(ChannelEditParam channelEditParam);
}

