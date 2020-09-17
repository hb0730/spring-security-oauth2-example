package com.hb0730.spring.oauth2.exmaple.page.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * spring security配置<br>
 * spring  security的WebSecurityConfigurerAdapter比security oauth2的ResourceServerConfigurerAdapter优先级要高
 *
 * @author bing_huang
 */
@Configuration
@EnableWebSecurity
@Order(1) // 可以尝试order去掉不同情况
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 注入 Spring 容器中, 在授权服务器中密码模式下,验证用户密码正确性
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页的路径 (/login)
                .loginPage("/login.html")
                //指定自定义form表单请求的路径
                .loginProcessingUrl("/authentication/form")
                // 认证失败后的跳转
                .failureUrl("/login.html?error")
                // 可设置true来任何时候到跳转 .defaultSuccessUrl("/hello2", true);
                .defaultSuccessUrl("/info",true)
                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .permitAll();
        //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
        http .csrf().disable();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
