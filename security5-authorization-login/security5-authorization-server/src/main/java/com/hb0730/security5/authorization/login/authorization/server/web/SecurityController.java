package com.hb0730.security5.authorization.login.authorization.server.web;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author bing_huang
 */
@Controller
@RequiredArgsConstructor
public class SecurityController {
    private final ConsumerTokenServices consumerTokenServices;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        return "欢迎访问";
    }

    /**
     * 获取当前用户
     *
     * @param principal 已认证的用户
     * @return 当前用户
     */
    @ResponseBody
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @RequestMapping("/oauth/login")
    public String login() {
        return "login";
    }

    /**
     * 登出
     *
     * @param request request
     * @param token   token
     * @return 是否成功
     */
    @ResponseBody
    @PostMapping("/oauth/signout")
    public String signout(HttpServletRequest request, @RequestHeader("Authorization") String token) {
        token = StringUtils.replace(token, "bearer ", "");
        consumerTokenServices.revokeToken(token);
        return "登出成功";
    }


    /**
     * 用户端点
     */
    @RequestMapping(value = "/oauth/userinfo", headers = {"authorization"})
    @ResponseBody
    public Principal user(Principal principal) {
        // 为何转为map
        // 当security5 Client请求过来时流程
        // Oauth2LoginAuthenticationFilter#attemptAuthentication 184
        // OAuth2LoginAuthenticationProvider#authenticate 110
        // DefaultOAuth2Service#loadUser
        // DefaultOAuth2User
//        BearerTokenAuthentication authentication = (BearerTokenAuthentication) SecurityContextHolder.getContext().getAuthentication();
//        Map<String, Object> map = new HashMap<>();
//        map.put("authorities", authentication.getAuthorities());
//        map.put("details", authentication.getDetails());
//        map.put("authenticated", authentication.isAuthenticated());
//        map.put("principal", authentication.getPrincipal());
//        map.put("credentials", authentication.getCredentials());
//        map.put("token", authentication.getToken());
//        map.put("tokenAttributes", authentication.getTokenAttributes());
//        map.put("name", authentication.getTokenAttributes().get("user_name"));
        return principal;
    }
}
