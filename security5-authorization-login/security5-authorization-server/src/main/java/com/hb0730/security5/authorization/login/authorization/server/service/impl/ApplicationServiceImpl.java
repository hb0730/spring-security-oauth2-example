package com.hb0730.security5.authorization.login.authorization.server.service.impl;

import com.hb0730.security5.authorization.login.authorization.server.model.Application;
import com.hb0730.security5.authorization.login.authorization.server.service.IApplicationService;
import org.springframework.stereotype.Service;

/**
 * 应用信息(项目)
 *
 * @author bing_huang
 */
@Service
public class ApplicationServiceImpl implements IApplicationService {
    @Override
    public Application findApplicationById(String appid) {
        Application application = new Application();
        application.setAppid(appid);
        if ("test1".equals(appid)) {

            application.setName("测试1");
            return application;
        } else if ("test2".equals(appid)) {
            application.setName("测试2");
            return application;
        }
        return null;
    }


}
