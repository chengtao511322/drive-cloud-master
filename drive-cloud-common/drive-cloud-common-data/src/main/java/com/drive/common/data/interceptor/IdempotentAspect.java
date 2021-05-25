/*
package com.drive.common.data.interceptor;
 
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.drive.common.core.biz.ResObject;
import com.drive.common.core.biz.SubResultCode;
import com.drive.common.data.annotation.Idempotent;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
 
*/
/**
 * @Description 解决幂等性
 * @Author qizhentao
 * @Date 2020/3/10 16:23
 * @Version 1.0
 *//*

@Component
@Aspect
@Log4j2
public class IdempotentAspect {
 
    //@Around("@annotation(com.chuangqi.core.rpc.Idempotent)")
    @Around("@annotation(Idempotent)")
    public Object around(ProceedingJoinPoint  jp) throws Throwable {
        try {
            //Class clazz = jp.getTarget().getClass();
            //String methodName = jp.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature) jp.getSignature()).getMethod().getParameterTypes();
            Method methdo = jp.getTarget().getClass().getMethod(jp.getSignature().getName(), parameterTypes);
            if (methdo.getAnnotation(Idempotent.class) != null) {
                // String value = methdo.getAnnotation(ApiOperation.class).value();
 
                // 1.获取request对象
                HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
                // 获取token
                String token = request.getHeader("idempotent-token");
                //Console.log("idempotent-token:{}", token);
 
                // 2.获取session或redis
                HttpSession session = request.getSession();
 
                // 3.判断是否重复提交
                String key = jp.getSignature() + "/" + Arrays.toString(jp.getArgs()); // 定义key名称
                //Console.log("方法key:{}", key);
                if (session.getAttribute(key) == null || !session.getAttribute(key).equals(token)) {
                    session.setAttribute(key, token);
                } else {
                    Console.error("重复提交：{}", key);
                    ResObject resObject = new ResObject(202,false,"重复提交", SubResultCode.DATA_IDEMPOTENT.subCode(),SubResultCode.DATA_IDEMPOTENT.subMsg());
                    return resObject;
                }
            }
        } catch (Exception ex) {
            ex.toString();
        }
 
        // 正常执行目标业务
        return jp.proceed();
    }
 
}*/
