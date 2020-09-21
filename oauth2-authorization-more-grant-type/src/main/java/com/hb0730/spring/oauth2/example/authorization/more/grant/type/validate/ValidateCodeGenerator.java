package com.hb0730.spring.oauth2.example.authorization.more.grant.type.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成
 *
 * @author bing_huang
 */
public interface ValidateCodeGenerator {
    /**
     * 生成验证码
     *
     * @param request 请求
     * @return 生成结果
     */
    String generate(ServletWebRequest request);
}
