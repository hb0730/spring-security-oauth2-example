package com.hb0730.oauth2.login.redirect.authorization.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bing_huang
 */
@Controller
public class SecurityController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/auth-redirect")
    public String authRedirect(Model model, HttpServletRequest request) {
        model.addAttribute("redirect_uri", request.getRequestURL().toString());
        return "result";
    }

}
