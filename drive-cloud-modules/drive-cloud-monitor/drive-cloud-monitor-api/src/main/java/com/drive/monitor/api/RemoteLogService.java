package com.drive.monitor.api;

import com.drive.common.core.biz.ResObject;
import com.drive.common.core.constant.ServiceNameConstants;
import com.drive.monitor.factory.LogFallbackFactory;
import com.drive.monitor.pojo.LoginLogEditParam;
import com.drive.monitor.pojo.OperLogEditParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 日志服务
 * @author DreamChan
 */
@FeignClient(contextId = "remoteLogService", value= ServiceNameConstants.MONITOR_SERVICE, fallbackFactory = LogFallbackFactory.class)
public interface RemoteLogService {

    /**
     * 保存系统操作日志
     *
     * @param operLogEditParam 日志实体
     * @return 结果
     */
    @PostMapping("/operLog")
    ResObject saveOperLog(@RequestBody OperLogEditParam operLogEditParam);

    /**
     * 保存访问记录
     *
     * @param loginLogEditParam 日志实体
     * @return 结果
     */
    @PostMapping("/loginLog")
    ResObject saveLoginLog(@RequestBody LoginLogEditParam loginLogEditParam);
}
