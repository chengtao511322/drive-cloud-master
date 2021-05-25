package com.drive.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.entity.MessageEntity;

/**
 *
 * 平台发送短信表 服务类
 *
 * @author xiaoguo
 */
public interface MessageService extends IService<MessageEntity>{

    /**
     * 分页查询
     * @param page
     * @param ew
     * @return
     */
    IPage<MessageEntity> pageList(Page page, Wrapper<MessageEntity> ew);
}

