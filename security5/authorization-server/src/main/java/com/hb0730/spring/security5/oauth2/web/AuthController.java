package com.hb0730.spring.security5.oauth2.web;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证控制类
 *
 * @author bing_huang
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final ConsumerTokenServices tokenServices;

    @GetMapping("/current/user")
    public Authentication getUser(HttpServletRequest request) {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 登出
     *
     * @param request 请求
     * @return 是否成功
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        String token = StringUtils.remove(request.getHeader("Authorization"), "Bearer ");
        if (org.springframework.util.StringUtils.hasText(token)) {
            tokenServices.revokeToken(token);
        } else {
            return "身份已过期";
        }
        return "退出成功";
    }
}
