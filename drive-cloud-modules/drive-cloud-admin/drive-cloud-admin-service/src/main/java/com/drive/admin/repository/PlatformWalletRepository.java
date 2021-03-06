package com.drive.admin.repository;

import com.drive.admin.pojo.dto.AccountFlowDetailInstallParam;
import com.drive.admin.pojo.dto.PlatformWalletEditParam;
import com.drive.admin.pojo.dto.PlatformWalletInstallParam;
import com.drive.admin.pojo.dto.PlatformWalletPageQueryParam;
import com.drive.common.core.base.BasicsRepository;
import com.drive.common.core.biz.ResObject;

/**
 *
 * 教练钱包表 服务类
 *
 * @author xiaoguo
 */
public interface PlatformWalletRepository extends BasicsRepository<PlatformWalletPageQueryParam, PlatformWalletEditParam,PlatformWalletInstallParam> {


    /**
     * 钱包结算
     * @return
     */
    ResObject walletSettle(String orderNo);

    /**
     * 通过订单钱包结算
     * @param orderNo
     * @return
     */
    ResObject settlementByOrder(String orderNo);

    /**
     * 单个钱包结算
     * @param orderNo
     * @return
     */
    ResObject settlementToWallet(AccountFlowDetailInstallParam accountFlowDetailInstallParam);

    /**
     * 钱包对账
     * @param walletId 钱包id
     */
    ResObject walletReconciliation(String walletId);
}

