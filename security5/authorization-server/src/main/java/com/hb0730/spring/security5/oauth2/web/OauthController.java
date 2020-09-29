package com.hb0730.spring.security5.oauth2.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义Oauth2自定义返回格式(json)
 *
 * @author bing_huang
 */
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {
    private final TokenEndpoint tokenEndpoint;
    private final TokenStore tokenStore;

    /**
     * get登录
     */
    @GetMapping("/token")
    public LinkedHashMap<String, Object> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    /**
     * post登录
     */
    @PostMapping("/token")
    public LinkedHashMap<String, Object> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    private LinkedHashMap<String, Object> custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        LinkedHashMap<String, Object> data = new LinkedHashMap<>(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken());
        }
        return data;
    }
}
