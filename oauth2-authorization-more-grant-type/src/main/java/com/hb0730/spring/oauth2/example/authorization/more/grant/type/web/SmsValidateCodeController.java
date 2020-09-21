package com.hb0730.spring.oauth2.example.authorization.more.grant.type.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * oauth2 控制器
 *
 * @author bing_huang
 */
@RestController
@RequestMapping("/auth")
public class SmsValidateCodeController {
    @PostMapping("/sms")
    public HttpEntity<?> sms() {
        return ResponseEntity.ok("ok");
    }
}
