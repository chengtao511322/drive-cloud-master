package com.drive.common.core.base;

import com.drive.common.core.exception.BizException;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface DriveClient {

    /**
     *
     * @param request
     * @param <T>
     * @return
     * @throws BizException
     */
    <T extends DriveResponse> T execute(DriveRequest<T> request) throws BizException;
}
