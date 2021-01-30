/*
package com.drive.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.drive.common.core.biz.R;
import com.drive.gateway.service.CaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

*/
/**
 * 验证码过滤器
 *
 * @author xiaoguo
 *//*

@Component
@Slf4j
public class AppAuthFilter extends AbstractGatewayFilterFactory<Object> {

    private final static String AUTH_URL = "/oauth/token";

    private static final String BASIC_ = "Basic ";

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    private static final String GRANT_TYPE = "grant_type";

    private static final String REFRESH_TOKEN = "refresh_token";

    @Autowired
    private CaptchaService captchaService;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();


            try {

                log.info("app鉴定");
               */
/* captchaService.checkCaptcha(request.getQueryParams().getFirst(CODE),
                        request.getQueryParams().getFirst(UUID));*//*

            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return exchange.getResponse().writeWith(
                        Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(R.failure(e.getMessage())))));
            }
            return chain.filter(exchange);
        };
    }
}
*/
