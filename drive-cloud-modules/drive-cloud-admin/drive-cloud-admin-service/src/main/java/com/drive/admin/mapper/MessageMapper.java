package com.drive.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.drive.admin.pojo.dto.StudentInfoPageQueryParam;
import com.drive.admin.pojo.entity.MessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 平台发送短信表 Mapper 接口
 *
 * @author xiaoguo
 */
public interface MessageMapper extends BaseMapper<MessageEntity> {

    /**
     * 分页查询
     * @param page
     * @param ew
     * @return
     */
    IPage<MessageEntity> pageList(Page page, @Param("ew") Wrapper<MessageEntity> ew);
}

