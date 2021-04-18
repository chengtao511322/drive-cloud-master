package com.drive.admin.repository;

import com.drive.admin.pojo.dto.PlatformWalletDetailEditParam;
import com.drive.admin.pojo.dto.PlatformWalletDetailInstallParam;
import com.drive.admin.pojo.dto.PlatformWalletDetailPageQueryParam;
import com.drive.common.core.base.BasicsRepository;

/**
 *
 * 教练钱包表明细 服务类
 *
 * @author xiaoguo
 */
public interface PlatformWalletDetailRepository extends BasicsRepository<PlatformWalletDetailPageQueryParam, PlatformWalletDetailEditParam,PlatformWalletDetailInstallParam> {
}

