package com.drive.monitor.factory;

import com.drive.common.core.biz.R;
import com.drive.common.core.biz.ResObject;
import com.drive.monitor.api.RemoteLogService;
import com.drive.monitor.pojo.LoginLogEditParam;
import com.drive.monitor.pojo.OperLogEditParam;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 服务降级处理
 * @author DreamChan
 */
@Component
public class LogFallbackFactory implements FallbackFactory<RemoteLogService> {


    @Override
    public RemoteLogService create(Throwable throwable) {
        return new RemoteLogService() {

            @Override
            public ResObject saveOperLog(OperLogEditParam operLogEditParam) {
                return R.failure("保存日志失败");
            }

            @Override
            public ResObject saveLoginLog(LoginLogEditParam loginLogEditParam) {
                return R.failure("保存日志失败");
            }
        };
    }
}
