package com.drive.admin.repository;

import com.drive.admin.pojo.dto.OperatorEditParam;
import com.drive.admin.pojo.dto.OperatorInstallParam;
import com.drive.admin.pojo.dto.OperatorPageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 运营商基础信息 服务类
 *
 * @author xiaoguo
 */
public interface OperatorRepository extends BasicsRepository<OperatorPageQueryParam, OperatorEditParam,OperatorInstallParam> {
}

