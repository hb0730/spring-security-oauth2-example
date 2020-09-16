package com.hb0730.spring.oauth2.example.resource.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * 资源服务器配置
 *
 * @author bing_huang
 */
@Configuration
@EnableResourceServer // 启动资源服务器配置
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    /***
     * token认证
     * @return 远程token服务 {@link RemoteTokenServices}
     */
    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
        tokenService.setClientId("client");
        tokenService.setClientSecret("secret");
        return tokenService;
    }
}
