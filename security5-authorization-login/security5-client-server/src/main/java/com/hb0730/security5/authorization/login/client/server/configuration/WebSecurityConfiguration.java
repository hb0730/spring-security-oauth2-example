package com.hb0730.security5.authorization.login.client.server.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author bing_huang
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().disable()
                .cors()
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
//                .logoutSuccessHandler()
        ;

        http
                .oauth2Login()

        ;

        http
                .oauth2Client()
        ;
    }
}
