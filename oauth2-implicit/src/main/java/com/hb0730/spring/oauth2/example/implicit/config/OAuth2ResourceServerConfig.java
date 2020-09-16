package com.hb0730.spring.oauth2.example.implicit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器（spring security oauth2）
 *
 * @author bing_huang
 */
@Configuration
@EnableResourceServer // 开启资源服务
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

}
