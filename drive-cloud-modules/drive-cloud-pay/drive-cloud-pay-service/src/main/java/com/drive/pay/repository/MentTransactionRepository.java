package com.drive.pay.repository;

import com.drive.common.core.base.BasicsRepository;
import com.drive.pay.pojo.dto.MentTransactionEditParam;
import com.drive.pay.pojo.dto.MentTransactionInstallParam;
import com.drive.pay.pojo.dto.MentTransactionPageQueryParam;

/**
 *
 * 支付交易流水信息表 服务类
 *
 * @author xiaoguo
 */
public interface MentTransactionRepository extends BasicsRepository<MentTransactionPageQueryParam, MentTransactionEditParam,MentTransactionInstallParam> {
}

