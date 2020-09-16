# spring security oauth2之密码登录

# configuration
1. `#Oauth2AuthorizationServerConfig` 主要定义的认证服务器的配置
2. `#OAuth2ResourceServerConfig` 主要定义的认证资源服务器的配置
3. `SecurityConfig` 主要定义的spring security的配置

# `WebSecurityConfigurerAdapter` 与 `ResourceServerConfigurerAdapter`
`WebSecurityConfigurerAdapter`默认情况下是spring security的http配置
`ResourceServerConfigurerAdapter`默认情况下是spring security oauth2的http配置
WebSecurityConfigurerAdapter的拦截顺序要先于ResourceServerConfigurerAdapter

文章:[WebSecurityConfigurerAdapter与ResourceServerConfigurerAdapter](https://www.jianshu.com/p/fe1194ca8ecd)

# 接口调用
<img src="https://github.com/hb0730/spring-security-oauth2-example/blob/master/doc/password/password-01.png" alt="1">

<img src="https://github.com/hb0730/spring-security-oauth2-example/blob/master/doc/password/password-02.png" alt="2">

<img src="https://github.com/hb0730/spring-security-oauth2-example/blob/master/doc/password/password-03.png" alt="3">



