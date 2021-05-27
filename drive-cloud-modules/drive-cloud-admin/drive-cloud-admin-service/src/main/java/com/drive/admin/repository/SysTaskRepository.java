package com.drive.admin.repository;

import com.drive.admin.pojo.dto.*;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 系统任务表 服务类
 *
 * @author chentao
 */
public interface SysTaskRepository extends BasicsRepository<SysTaskPageQueryParam, SysTaskEditParam,SysTaskInstallParam>{

    /**
     * 开启定时任务
     * @param taskId 任务id编号
     */
    ResObject startTask(String taskId);

    /**
     * 暂停定时任务
     */
    ResObject endTask(String taskId);

    /**
     * 删除定时任务
     */
}

