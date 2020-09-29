package com.hb0730.spring.security5.resource.server.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bing_huang
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public Authentication index(Authentication authentication) {
        return authentication;
    }
}
