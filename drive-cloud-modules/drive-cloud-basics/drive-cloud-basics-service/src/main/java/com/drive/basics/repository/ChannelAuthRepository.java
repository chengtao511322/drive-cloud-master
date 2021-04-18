package com.drive.basics.repository;

import com.drive.basics.pojo.dto.ChannelAuthEditParam;
import com.drive.basics.pojo.dto.ChannelAuthInstallParam;
import com.drive.basics.pojo.dto.ChannelAuthPageQueryParam;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 菜单 按钮 用户拥有权限管理 服务类
 *
 * @author xiaoguo
 */
public interface ChannelAuthRepository extends BasicsRepository<ChannelAuthPageQueryParam, ChannelAuthEditParam,ChannelAuthInstallParam> {

    /**
     * 修改用户操作权限
     * @param channelAuthEditParam
     * @return
     */
    ResObject updateChannelAuth(ChannelAuthEditParam channelAuthEditParam);

    /**
     * 复制用户权限
     * @param userId
     * @return
     */
    ResObject copyChannelAuth(ChannelAuthEditParam channelAuthEditParam);
}

