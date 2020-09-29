package com.hb0730.spring.security5.resource.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 资源服务器
 *
 * @author bing_huang
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityServerConfiguration extends WebSecurityConfigurerAdapter {
    private final OAuth2ResourceServerProperties properties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors();
        http.oauth2ResourceServer()
                .opaqueToken()
                .introspectionClientCredentials(
                        properties.getOpaquetoken().getClientId(),
                        properties.getOpaquetoken().getClientSecret())
                .introspectionUri(properties.getOpaquetoken().getIntrospectionUri());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
