package com.drive.auth.config;

import com.drive.auth.exception.AuthExceptionTranslator;
import com.drive.common.core.constant.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证服务配置
 *
 * 提供以下接口
 * 1. /oauth/authorize：授权端点。
 * 2. /oauth/token：获取token。
 * 3. /oauth/confirm_access：用户确认授权提交端点。
 * 4. /oauth/error：授权服务错误信息端点。
 * 5. /oauth/check_token：用于资源服务访问的令牌解析端点。
 * 6. /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。
 * 7. /oauth/logout: 退出
 *
 * @author xiaoguo
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomTokenEnhancer customTokenEnhancer;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //开启/oauth/token_key验证端口无权限访问
        security.tokenKeyAccess("permitAll()");
        //开启/oauth/check_token验证端口权限访问
        security.checkTokenAccess("isAuthenticated()");
        //允许表单认证
        security.allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端
        clients.withClientDetails(jdbcClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .tokenEnhancer(customTokenEnhancer)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .pathMapping("/oauth/confirm_access", "/token/confirm_access")
                .exceptionTranslator(new AuthExceptionTranslator());
                //.tokenServices(defaultTokenServices());
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     *
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(customClientDetailsService);
        // token有效期自定义设置，90天
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24 * 90);
        // refresh_token 90天
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 90);
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
        return tokenStore;
    }
}
