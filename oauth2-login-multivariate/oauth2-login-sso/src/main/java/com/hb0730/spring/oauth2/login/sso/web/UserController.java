package com.hb0730.spring.oauth2.login.sso.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 为了支持oauth2Login(), 为何不用oauth/check_token ，
 * /oauth/check_token 是basic认证
 *
 * @author bing_huang
 */
@RestController
public class UserController {
    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.err.println(principal);
        return principal;
    }
}
