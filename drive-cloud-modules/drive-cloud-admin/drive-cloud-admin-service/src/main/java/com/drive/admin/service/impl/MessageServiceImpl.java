package com.drive.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.mapper.MessageMapper;
import com.drive.admin.service.MessageService;
import com.drive.common.core.base.BaseService;
import com.drive.admin.pojo.entity.MessageEntity;

import org.springframework.stereotype.Service;

/**
 * 平台发送短信表 服务实现类
 *
 * @author xiaoguo
 */
@Service
public class MessageServiceImpl extends BaseService<MessageMapper, MessageEntity> implements MessageService {

    @Override
    public IPage<MessageEntity> pageList(Page page, Wrapper<MessageEntity> ew) {
        return this.getBaseMapper().pageList(page,ew);
    }
}

