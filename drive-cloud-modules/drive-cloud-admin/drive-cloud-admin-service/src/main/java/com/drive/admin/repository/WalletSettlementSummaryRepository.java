package com.drive.admin.repository;

import com.alipay.api.AlipayApiException;
import com.drive.admin.pojo.dto.*;
import com.drive.common.core.base.BaseRepository;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.exception.BizException;

/**
 *
 *  服务类
 *
 * @author chentao
 */
public interface WalletSettlementSummaryRepository extends BasicsRepository<WalletSettlementSummaryPageQueryParam, WalletSettlementSummaryEditParam,WalletSettlementSummaryInstallParam>{

    /**
     * 根据ID 审核数据
     * @param id
     * @return
     */
    ResObject examine(String id);

    /**驳回数据
     *
     * @param walletSettlementSummaryEditParam
     * @return
     */
    ResObject unusual(WalletSettlementSummaryEditParam walletSettlementSummaryEditParam);

    /**
     * 操作状态
     * @param walletSettlementSummaryEditParam
     * @return
     */
    ResObject operationStatus(WalletSettlementSummaryEditParam walletSettlementSummaryEditParam);

    /**
     * 清算方法
     * @param walletSettlementSummaryEditParam
     * @return
     */
    ResObject executeSingleSettlement(WalletSettlementSummaryEditParam walletSettlementSummaryEditParam) throws Exception;
}

