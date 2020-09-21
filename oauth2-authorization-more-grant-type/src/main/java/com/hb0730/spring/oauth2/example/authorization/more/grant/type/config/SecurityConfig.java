package com.hb0730.spring.oauth2.example.authorization.more.grant.type.config;

import com.hb0730.spring.oauth2.example.authorization.more.grant.type.auth.sms.SmsAuthenticationSecurityConfig;
import com.hb0730.spring.oauth2.example.authorization.more.grant.type.validate.ValidateCodeFilter;
import com.hb0730.spring.oauth2.example.authorization.more.grant.type.validate.ValidateCodeGranterFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * @author bing_huang
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ValidateCodeFilter validateCodeFilter;
    private final ValidateCodeGranterFilter validateCodeGranterFilter;
    private final SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    /**
     * 密码加密方式，spring 5 后必须对密码进行加密
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 创建两个内存用户
     * 用户名 user 密码 123456 角色 ROLE_USER
     * 用户名 admin 密码 admin 角色 ROLE_ADMIN
     *
     * @return InMemoryUserDetailsManager
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("123456"))
                .authorities("ROLE_USER").build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities("ROLE_ADMIN").build());
        manager.createUser(User.withUsername("13712341234")
                .password(passwordEncoder().encode("123456"))
                .authorities("ROLE_ADMIN").build());
        return manager;
    }

    /**
     * 认证管理
     *
     * @return 认证管理对象
     * @throws Exception 认证异常信息
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .apply(smsAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers("/code/*").permitAll()
                .antMatchers("/auth/sms").permitAll()
                .antMatchers("/custom/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .and()
                .httpBasic();


        http
                .addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterBefore(validateCodeGranterFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
