package com.drive.basics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.drive.basics.pojo.dto.AppVersionEditParam;
import com.drive.basics.pojo.entity.AppVersionEntity;
import com.drive.common.core.biz.ResObject;

/**
 *
 * app版本控制信息表 服务类
 *
 * @author xiaoguo
 */
public interface AppVersionService extends IService<AppVersionEntity>{

    /**
     * 事务回滚
     * @param appVersionEditParam
     * @return
     */
    ResObject createOrderRollback(AppVersionEditParam appVersionEditParam);
}

