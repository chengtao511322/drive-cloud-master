package com.drive.auth.config;

import com.drive.common.core.constant.SecurityConstants;
import com.drive.common.security.domain.LoginUser;
import com.google.common.collect.Maps;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 自定义 生成令牌
 *
 * @author DreamChan
 */
@Component
public class CustomTokenEnhancer implements TokenEnhancer {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        String grantType = oAuth2Authentication.getOAuth2Request().getGrantType();
        if (grantType.equals("password")){
            LoginUser loginUser = (LoginUser)oAuth2Authentication.getPrincipal();

            final Map<String, Object> additionalInfo = Maps.newHashMap();

            additionalInfo.put(SecurityConstants.TENANT_ID, loginUser.getTenantId());
            additionalInfo.put(SecurityConstants.OPERATION_ID, loginUser.getOperationId());
            additionalInfo.put(SecurityConstants.DETAILS_USER_ID, loginUser.getUserId());
            additionalInfo.put(SecurityConstants.DETAILS_USERNAME, loginUser.getUsername());
            ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        }
        return oAuth2AccessToken;
    }
}
