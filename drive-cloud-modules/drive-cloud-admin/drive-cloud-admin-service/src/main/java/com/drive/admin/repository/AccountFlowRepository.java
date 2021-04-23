package com.drive.admin.repository;

import com.drive.admin.pojo.dto.*;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;

import java.util.List;

/**
 *
 * 平台账务流水 服务类
 *
 * @author xiaoguo
 */
public interface AccountFlowRepository extends BasicsRepository<AccountFlowPageQueryParam, AccountFlowEditParam,AccountFlowInstallParam> {


    /**
     * 查询出账户流水
     * @return
     */
    ResObject createTestPassVIPCoachFlowDetail(StudentTestEnrollEditParam studentTestEnrollEditParam);

    /**
     * 设置流水
     * @param accountFlowDetailEditParams
     * @return
     */
    ResObject settlementByFlowDetailList(List<AccountFlowDetailEditParam> accountFlowDetailEditParams);

}

