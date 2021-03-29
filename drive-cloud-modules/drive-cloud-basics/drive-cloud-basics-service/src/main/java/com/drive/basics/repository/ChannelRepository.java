package com.drive.basics.repository;

import com.drive.basics.pojo.dto.ChannelEditParam;
import com.drive.basics.pojo.dto.ChannelPageQueryParam;
import com.drive.basics.pojo.entity.ChannelEntity;
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


    /**
     * 通过 parentId 查询项目
     * @param parentId
     * @return
     */
    ResObject getChannelByParentId(String parentId);

    /**
     * 获取父项目
     * @return
     */
    ResObject getParentList();


    /**
     * 数据移东
     * @param sortNum
     * @return
     */
    ResObject move(ChannelEditParam channelEditParam);
}

