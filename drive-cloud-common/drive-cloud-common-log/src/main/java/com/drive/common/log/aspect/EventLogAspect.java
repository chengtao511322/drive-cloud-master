package com.drive.common.log.aspect;

import com.drive.common.core.utils.IpUtils;
import com.drive.common.core.utils.JsonUtil;
import com.drive.common.core.utils.ServletUtils;
import com.drive.common.core.utils.StringUtils;
import com.drive.common.log.annotation.EventLog;
import com.drive.common.security.domain.LoginUser;
import com.drive.common.security.utils.SecurityUtils;
import com.drive.monitor.pojo.OperLogEditParam;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;


/**
 * 操作日志处理
 *
 * @author xiaoguo
 */
@Order(10)
@Aspect
@Component
@Slf4j
public class EventLogAspect {

    //@Autowired
    //private RemoteLogService logService;

    @SneakyThrows
    @Around("@annotation(eventLog)")
    public Object around(ProceedingJoinPoint point, EventLog eventLog) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        log.info("EventLogAspect 类名:<{}>, 方法:<{}>", strClassName, strMethodName);


        OperLogEditParam operLogEditParam = buildOperLog();

        Object[] bodyArgs = point.getArgs();

        operLogEditParam.setMessage(eventLog.message());
        operLogEditParam.setBusinessType(eventLog.businessType().getCode());
        long startTime = Instant.now().toEpochMilli();

        Object result = point.proceed();

        long endTime = Instant.now().toEpochMilli();
        operLogEditParam.setExecuteTime(endTime - startTime);

        log.info("{}",operLogEditParam);
        //logService.saveOperLog(operLogEditParam);
        return result;
    }

    @SneakyThrows
    private OperLogEditParam buildOperLog() {
        HttpServletRequest request = ServletUtils.getRequest();
        OperLogEditParam operLogEditParam = new OperLogEditParam();
        operLogEditParam.setRequestDate(LocalDateTime.now());

        LoginUser loginUser = SecurityUtils.getLoginUser();
        operLogEditParam.setUserId(loginUser.getUserId());
        operLogEditParam.setUserName(loginUser.getUsername());
        operLogEditParam.setRequestUrl(request.getRequestURI());
        operLogEditParam.setRequestMethod(request.getMethod());
        operLogEditParam.setOperIp(IpUtils.getIpAddr(ServletUtils.getRequest()));

        String formParam = JsonUtil.toJson(request.getParameterMap());
        if (StringUtils.isNotBlank(formParam)) {
            // 获取 form 参数
            operLogEditParam.setRequestParam(formParam);
        } else {
            operLogEditParam.setRequestParam("请求参数为空");
        }
        return operLogEditParam;
    }

}
