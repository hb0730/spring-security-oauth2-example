package com.hb0730.oauth2.login.redirect.authorization.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户登录
 *
 * @author bing_huang
 */
@Controller
public class SecurityController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
