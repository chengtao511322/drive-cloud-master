package com.drive.admin.repository;

import com.drive.admin.pojo.dto.AccountFlowDetailEditParam;
import com.drive.admin.pojo.dto.AccountFlowDetailInstallParam;
import com.drive.admin.pojo.dto.AccountFlowDetailPageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 平台账务流水明细 服务类
 *
 * @author xiaoguo
 */
public interface AccountFlowDetailRepository extends BasicsRepository<AccountFlowDetailPageQueryParam, AccountFlowDetailEditParam,AccountFlowDetailInstallParam> {
}

