package com.hb0730.spring.oauth2.login.sso.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bing_huang
 */
@RestController
@RequestMapping
public class IndexController {
    @RequestMapping("/")
    public Authentication index() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
