package com.drive.admin.design.impl;

import com.drive.admin.design.StatusState;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClearingSuccessStatusState implements StatusState {

    @Override
    public ResObject statusService() {
        log.info(">>>切换为清算成功状态..");
        return R.success("清算成功");
    }
}