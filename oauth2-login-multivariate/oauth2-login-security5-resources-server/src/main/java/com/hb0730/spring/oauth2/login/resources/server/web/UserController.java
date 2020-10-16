package com.hb0730.spring.oauth2.login.resources.server.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 为了支持oauth2Login(), 为何不用oauth/check_token ，
 * /oauth/check_token 是basic认证
 *
 * @author bing_huang
 */
@RestController
public class UserController {
    @RequestMapping("/user/me")
    public Map<String, Object> user(Principal principal) {
        // 为何转为map
        // 当security5 Client请求过来时流程
        // Oauth2LoginAuthenticationFilter#attemptAuthentication 184
        // OAuth2LoginAuthenticationProvider#authenticate 110
        // DefaultOAuth2Service#loadUser
        // DefaultOAuth2User
        BearerTokenAuthentication authentication = (BearerTokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> map = new HashMap<>();
        map.put("authorities", authentication.getAuthorities());
        map.put("details", authentication.getDetails());
        map.put("authenticated", authentication.isAuthenticated());
        map.put("principal", authentication.getPrincipal());
        map.put("credentials", authentication.getCredentials());
        map.put("token", authentication.getToken());
        map.put("tokenAttributes", authentication.getTokenAttributes());
        map.put("name", authentication.getTokenAttributes().get("user_name"));
        return map;
    }
}
