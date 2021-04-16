package com.drive.basics.repository;

import com.drive.basics.pojo.dto.DriveFlowEditParam;
import com.drive.basics.pojo.dto.DriveFlowInstallParam;
import com.drive.basics.pojo.dto.DriveFlowPageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 流程信息管理 服务类
 *
 * @author xiaoguo
 */
public interface DriveFlowRepository extends BasicsRepository<DriveFlowPageQueryParam, DriveFlowEditParam,DriveFlowInstallParam> {
}

