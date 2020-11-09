package com.hb0730.oauth2.login.redirect.authorization.server.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义授权码页面
 *
 * @author bing_huang
 */
@Controller
@SessionAttributes("authorizationRequest")
public class AuthorizationController {
    // @see WhitelabelApprovalEndpoint
    // @see AuthorizationEndpoint

    @RequestMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("authorization");
        view.addObject("clientId", authorizationRequest.getClientId());
        view.addObject("scopeList", authorizationRequest.getScope());
        return view;
    }
}
