package com.hb0730.spring.oauth2.example.authorization.expansion.web;

import com.hb0730.spring.oauth2.example.authorization.expansion.config.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author bing_huang
 */
@Controller
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OauthController {
    private final SecurityProperties securityProperties;

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("action", securityProperties.getLoginProcessingUrl());
        return "form-login";
    }

}
