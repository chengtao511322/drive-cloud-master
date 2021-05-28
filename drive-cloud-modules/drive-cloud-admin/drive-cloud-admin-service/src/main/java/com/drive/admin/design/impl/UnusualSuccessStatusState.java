package com.drive.admin.design.impl;

import com.drive.admin.design.StatusState;
import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Component
@Slf4j
public class UnusualSuccessStatusState implements StatusState {
    @Override
    public ResObject statusService() {
        log.info(">>>切换为驳回成功状态..");
        return R.success("驳回成功");
    }
}
