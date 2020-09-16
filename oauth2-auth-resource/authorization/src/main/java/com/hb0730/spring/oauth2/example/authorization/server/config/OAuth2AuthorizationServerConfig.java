package com.hb0730.spring.oauth2.example.authorization.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 授权服务器配置
 *
 * @author bing_huang
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Qualifier("authenticationManagerBean")
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    /**
     * 配置 Spring MVC Controller
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 用户信息 : refresh_token模式通过username查询
                .userDetailsService(userDetailsService)
                //password模式，验证密码，返回用户信息
                .authenticationManager(authenticationManager);
    }

    /**
     * 配置客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 内存模式:一般有jdbc模式与jwt模式, oauth2新增redis存储模式
        clients.inMemory()
                //client_id
                .withClient("client")
                //密码不加密
                .secret("{noop}secret")
                // 授权方式 password:密码方式,client_credentials:客户端方式,authorization_code: 授权码方式以及imlpicit简单方式
                //refresh_token令牌刷新
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                //认证回调
                .redirectUris("http://localhost:8080/tonr2/sparklr/photos");
    }

    /**
     * 配置 Spring Security
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 放开check_token
        security.checkTokenAccess("permitAll()");
    }
}
