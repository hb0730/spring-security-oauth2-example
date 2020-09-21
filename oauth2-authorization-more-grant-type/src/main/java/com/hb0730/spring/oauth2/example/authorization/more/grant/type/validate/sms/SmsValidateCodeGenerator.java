package com.hb0730.spring.oauth2.example.authorization.more.grant.type.validate.sms;

import com.hb0730.spring.oauth2.example.authorization.more.grant.type.util.RandomCode;
import com.hb0730.spring.oauth2.example.authorization.more.grant.type.validate.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 手机验证码生成器
 *
 * @author bing_huang
 */
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {
    @Override
    public String generate(ServletWebRequest request) {
        // 定义手机验证码生成策略，可以使用 request 中从请求动态获取生成策略
        // 可以从配置文件中读取生成策略
        return RandomCode.random(4, true);
    }
}
