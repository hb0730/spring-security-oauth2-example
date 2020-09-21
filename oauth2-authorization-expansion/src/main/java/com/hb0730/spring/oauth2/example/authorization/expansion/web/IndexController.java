package com.hb0730.spring.oauth2.example.authorization.expansion.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bing_huang
 */
@RestController
@RequestMapping
public class IndexController {

    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public Authentication index(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
