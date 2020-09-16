package com.hb0730.spring.oauth2.example.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security配置<br>
 * spring  security的WebSecurityConfigurerAdapter比security oauth2的ResourceServerConfigurerAdapter优先级要高
 *
 * @author bing_huang
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

}
