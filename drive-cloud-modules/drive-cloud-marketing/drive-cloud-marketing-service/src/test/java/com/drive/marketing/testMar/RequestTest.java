package com.drive.marketing.testMar;

import com.drive.common.core.base.AbstractDriveClient;
import com.drive.common.core.base.DriveClient;
import com.drive.common.core.base.DriveRequest;
import com.drive.common.core.base.DriveResponse;
import com.drive.common.core.exception.BizException;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
public class RequestTest {

    public static void main(String[] args) {
        AbstractDriveClient abstractDriveClient = new AbstractDriveClient() {
            @Override
            public <T extends DriveResponse> T execute(DriveRequest<T> request) throws BizException {
                return null;
            }
        };
    }
}
