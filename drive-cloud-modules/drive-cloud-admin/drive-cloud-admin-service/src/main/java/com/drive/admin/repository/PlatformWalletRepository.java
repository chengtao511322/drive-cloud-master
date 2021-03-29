package com.drive.admin.repository;

import com.drive.admin.pojo.dto.*;
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
}

