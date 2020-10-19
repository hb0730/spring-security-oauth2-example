package com.hb0730.spring.security5.oauth2.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author bing_huang
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors();

        // 授权服务器 重定向地址
        // yaml 一致
        // https://docs.spring.io/spring-security/site/docs/5.4.1/reference/html5/#oauth2login-advanced
        http.oauth2Login().redirectionEndpoint((redirection) -> redirection.baseUri("/client"));

    }
}
