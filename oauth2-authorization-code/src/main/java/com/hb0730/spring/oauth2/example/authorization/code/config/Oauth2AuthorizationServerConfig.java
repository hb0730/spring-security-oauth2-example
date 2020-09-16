package com.hb0730.spring.oauth2.example.authorization.code.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 授权服务器
 *
 * @author bing_huang
 */
@Configuration
@EnableAuthorizationServer // 开启授权服务
@RequiredArgsConstructor
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 客户端配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 内存模式
        clients.inMemory()
                //客户端id
                .withClient("client")
                //授权类型
                .authorizedGrantTypes("authorization_code")
                // 权限(一般填写 security 权限)
                .authorities("ROLE_CLIENT")
                // (授权范围). 如果是空,就是不受限制
                .scopes("read", "write")
                // 重定向地址
                .redirectUris("http://localhost:8080")
                // 客户端密钥
                .secret("{noop}secret");
    }
}
