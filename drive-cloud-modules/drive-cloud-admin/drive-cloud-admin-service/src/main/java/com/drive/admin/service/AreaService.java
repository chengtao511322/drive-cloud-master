package com.drive.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.admin.pojo.entity.AreaEntity;

/**
 *
 *  服务类
 *
 * @author xiaoguo
 */
public interface AreaService extends IService<AreaEntity>{

    //
    AreaEntity getByBaCode(String baCode);
}

