/*
 * Copyright (C), 2020, 安徽艾伦家居饰品有限公司
 * FileName: AuthSignatureFilter
 * Author:   Allen
 * Date:     2020/8/29 17:09
 * Description: 鉴权过滤
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.drive.gateway.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;


/**
 * 〈鉴权过滤〉
 *
 * @author Allen
 * @date 2020/8/29
 * @since 1.0.0
 */
@Slf4j
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    /**
     * 注入gatewayFilterConfiguration
     */


    /**
     * 注入StringRedisTemplate
     */


    /**
     * 过滤器
     * @param exchange ServerWebExchange
     * @param chain GatewayFilter
     * @return 过滤信息
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String token = extractToken(request);
        if (StringUtils.isNotBlank(token)) {
            try {
                log.debug("[本次请求的Token值]:{}", token);
                return chain.filter(exchange);
            } catch (Exception e) {
               log.error("[gateway获取Token的请求异常]：{}", e.getMessage());
            }
        }

        String path = request.getURI().getPath();
        log.info("[请求地址]：{}", path);

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    /**
     * 获得Token值
     * @param request ServerHttpRequest
     * @return Token值
     */
    protected String extractToken(ServerHttpRequest request) {
        String authToken = request.getHeaders().getFirst("Authorization");
        if (authToken != null) {
            authToken = "access:" + authToken.substring("Bearer".length()).trim();
        }
        if (StringUtils.isBlank(authToken)) {
            String accessToken = request.getQueryParams().getFirst("access_token");
            if (accessToken != null) {
                authToken =  "access:" + accessToken.trim();
            }
        }
        return authToken;
    }

    /**
     * 设置Order顺序
     * @return 顺序号
     */
    @Override
    public int getOrder() {
        return -1;
    }

}