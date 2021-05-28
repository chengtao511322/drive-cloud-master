package com.drive.admin.design.impl;

import com.drive.admin.design.StatusState;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $ 审核通过
 * @Param $
 * @return $
 **/
@Slf4j
@Component
public class PassAuditStatusState implements StatusState {
    @Override
    public ResObject statusService() {
        log.info(">>>切换为审核通过状态<<<");
        return R.success("审核通过");
    }
}
