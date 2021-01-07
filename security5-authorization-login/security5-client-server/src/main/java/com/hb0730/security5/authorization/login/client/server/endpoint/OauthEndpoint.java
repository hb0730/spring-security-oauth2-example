package com.hb0730.security5.authorization.login.client.server.endpoint;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author bing_huang
 */
@Controller
public class OauthEndpoint {
    @RequestMapping("/oauth/current/user")
    @ResponseBody
    public Authentication current(Authentication authentication) {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
