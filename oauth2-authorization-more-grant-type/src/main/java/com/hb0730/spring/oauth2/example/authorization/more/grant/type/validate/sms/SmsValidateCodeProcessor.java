package com.hb0730.spring.oauth2.example.authorization.more.grant.type.validate.sms;

import com.hb0730.spring.oauth2.example.authorization.more.grant.type.validate.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**手机验证码处理器
 * @author bing_huang
 */
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor {
    @Override
    protected void send(ServletWebRequest request, String validateCode) {
        System.out.println(request.getParameter("sms") +
                "手机验证码发送成功，验证码为：" + validateCode);
    }

}
