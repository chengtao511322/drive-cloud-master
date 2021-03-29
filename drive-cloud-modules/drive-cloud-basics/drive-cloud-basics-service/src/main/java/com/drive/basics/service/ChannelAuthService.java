package com.drive.basics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.basics.pojo.entity.ChannelAuthEntity;

/**
 *
 * 菜单 按钮 用户拥有权限管理 服务类
 *
 * @author xiaoguo
 */
public interface ChannelAuthService extends IService<ChannelAuthEntity>{

    /**
     * 根据条件删除数据
     * @param channelAuthEditParam
     * @return
     */
    Boolean deleteCondition(ChannelAuthEditParam channelAuthEditParam);
}

