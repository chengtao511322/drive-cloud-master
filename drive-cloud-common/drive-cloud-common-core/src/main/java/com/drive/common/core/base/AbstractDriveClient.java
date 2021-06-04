package com.drive.common.core.base;

import com.drive.common.core.exception.BizException;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public abstract class AbstractDriveClient implements DriveClient{

    private <T extends DriveResponse> T _execute(DriveRequest<T> request) throws BizException {
        long beginTime = System.currentTimeMillis();
        T tRsp = null;
        tRsp.setData("成功");
        return tRsp;
    }
}
