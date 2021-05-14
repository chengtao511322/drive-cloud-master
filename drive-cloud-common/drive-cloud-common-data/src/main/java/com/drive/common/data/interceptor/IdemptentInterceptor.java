package com.drive.common.data.interceptor;

import com.drive.common.core.exception.BizException;
import com.drive.common.data.annotation.Idemptent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/*@Order(10)
@Aspect
@Component
@Slf4j*/
public class IdemptentInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        Idemptent annotation = method.getAnnotation(Idemptent.class);
        if (annotation != null){
            //进行幂等性校验
            checkToken(request);
        }

        return true;
    }


    @Autowired
    private  RedisTemplate redisTemplate;

    //幂等性校验
    private void checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)){
            throw new BizException(500,"非法参数");
        }

        boolean delResult = redisTemplate.delete(token);
        if (!delResult){
            //删除失败
            throw new BizException(500,"重复请求");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
