package com.drive.admin.repository;

import com.drive.common.core.biz.ResObject;

/**
 * 测试分布式事务
 */
public interface AccountRepository {

    /**
     * 添加金额
     * @param account
     * @return
     */
    ResObject increaseAmount(String account);
}
