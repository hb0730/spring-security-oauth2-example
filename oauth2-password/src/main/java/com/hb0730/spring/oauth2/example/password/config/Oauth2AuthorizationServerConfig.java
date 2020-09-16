package com.hb0730.spring.oauth2.example.password.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 授权服务器
 *
 * @author bing_huang
 */
@Configuration
@EnableAuthorizationServer // 开启授权服务
@RequiredArgsConstructor
public class Oauth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Qualifier("authenticationManagerBean")
    private final AuthenticationManager authenticationManager;

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
                .authorizedGrantTypes("password")
                // 权限(一般填写 security 权限)
                .authorities("ROLE_CLIENT")
                // (授权范围). 如果是空,就是不受限制
                .scopes("read", "write")
                // 客户端密钥
                .secret("{noop}secret");
    }

    /**
     * 配置 Spring MVC Controller
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // password 模式, 验证密码, 返回用户登录信息
        endpoints.authenticationManager(authenticationManager);
    }
}
