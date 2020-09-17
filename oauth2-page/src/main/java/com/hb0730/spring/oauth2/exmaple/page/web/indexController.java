package com.hb0730.spring.oauth2.exmaple.page.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bing_huang
 */
@RestController
public class indexController {
    @GetMapping("/info")
    public Object getInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
