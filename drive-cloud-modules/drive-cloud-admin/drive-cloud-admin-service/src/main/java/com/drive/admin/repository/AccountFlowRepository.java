package com.drive.admin.repository;

import com.drive.admin.pojo.dto.AccountFlowEditParam;
import com.drive.admin.pojo.dto.AccountFlowInstallParam;
import com.drive.admin.pojo.dto.AccountFlowPageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 平台账务流水 服务类
 *
 * @author xiaoguo
 */
public interface AccountFlowRepository extends BasicsRepository<AccountFlowPageQueryParam, AccountFlowEditParam,AccountFlowInstallParam> {
}

