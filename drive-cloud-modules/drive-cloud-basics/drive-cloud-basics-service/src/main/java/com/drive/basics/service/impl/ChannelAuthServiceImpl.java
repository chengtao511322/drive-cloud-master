package com.drive.basics.service.impl;

import com.drive.basics.mapper.ChannelAuthMapper;
import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.basics.pojo.entity.ChannelAuthEntity;
import com.drive.basics.service.ChannelAuthService;
import com.drive.common.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 菜单 按钮 用户拥有权限管理 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class ChannelAuthServiceImpl extends BaseService<ChannelAuthMapper, ChannelAuthEntity> implements ChannelAuthService {

    @Autowired
    private ChannelAuthMapper channelAuthMapper;

    @Override
    public Boolean deleteCondition(ChannelAuthEditParam channelAuthEditParam) {
        int result = channelAuthMapper.deleteCondition(channelAuthEditParam);
        return result > 0? true :false;
    }
}

