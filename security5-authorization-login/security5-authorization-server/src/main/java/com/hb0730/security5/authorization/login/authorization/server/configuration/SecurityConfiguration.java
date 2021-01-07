package com.hb0730.security5.authorization.login.authorization.server.configuration;

import com.hb0730.security5.authorization.login.authorization.server.handler.WebLoginFailureHandler;
import com.hb0730.security5.authorization.login.authorization.server.handler.WebLoginSuccessHandler;
import com.hb0730.security5.authorization.login.authorization.server.handler.WebLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.util.matcher.*;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bing_huang
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final WebLoginSuccessHandler webLoginSuccessHandler;
    private final WebLoginFailureHandler webLoginFailureHandler;
    private final WebLogoutSuccessHandler webLogoutSuccessHandler;
    private final UserDetailsService userDetailServiceImpl;
    private final OpaqueTokenIntrospector customAuthoritiesOpaqueTokenIntrospector;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/asset/**", "*.css", "*.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/oauth/login")
                .loginProcessingUrl("/oauth/login")
                .successHandler(webLoginSuccessHandler)
                .failureHandler(webLoginFailureHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/oauth/logout")
                .logoutSuccessHandler(webLogoutSuccessHandler)
                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                //资源拦截问题
                //授权回跳失败
                .requestCache()
                .requestCache(getRequestCache(http))

        ;

        http
                .oauth2ResourceServer()
                .opaqueToken()
                .introspector(customAuthoritiesOpaqueTokenIntrospector)
        ;


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private RequestCache getRequestCache(HttpSecurity http) {
        HttpSessionRequestCache defaultCache = new HttpSessionRequestCache();
        defaultCache.setRequestMatcher(createDefaultSavedRequestMatcher(http));
        return defaultCache;
    }

    private RequestMatcher createDefaultSavedRequestMatcher(HttpSecurity http) {
        RequestMatcher notFavIcon = new NegatedRequestMatcher(new AntPathRequestMatcher(
                "/**/favicon.*"));

        RequestMatcher notXRequestedWith = new NegatedRequestMatcher(
                new RequestHeaderRequestMatcher("X-Requested-With", "XMLHttpRequest"));


        List<RequestMatcher> matchers = new ArrayList<>();
        RequestMatcher getRequests = new AntPathRequestMatcher("/**", "GET");
        matchers.add(0, getRequests);
        matchers.add(notFavIcon);
        matchers.add(notMatchingMediaType(http, MediaType.APPLICATION_JSON));
        matchers.add(notXRequestedWith);
        matchers.add(notMatchingMediaType(http, MediaType.MULTIPART_FORM_DATA));
        matchers.add(notMatchingMediaType(http, MediaType.TEXT_EVENT_STREAM));

        return new AndRequestMatcher(matchers);
    }

    private RequestMatcher notMatchingMediaType(HttpSecurity http, MediaType mediaType) {
        ContentNegotiationStrategy contentNegotiationStrategy = http.getSharedObject(ContentNegotiationStrategy.class);
        if (contentNegotiationStrategy == null) {
            contentNegotiationStrategy = new HeaderContentNegotiationStrategy();
        }

        MediaTypeRequestMatcher mediaRequest = new MediaTypeRequestMatcher(contentNegotiationStrategy, mediaType);
        mediaRequest.setIgnoredMediaTypes(Collections.singleton(MediaType.ALL));
        return new NegatedRequestMatcher(mediaRequest);
    }
}
