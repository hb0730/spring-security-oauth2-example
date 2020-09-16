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

# 默认地址 `org.springframework.security.oauth2.provider.endpoint`
> /oauth/authorize 默认授权地址 
> /oauth/token 获取token地址 
> /oauth/confirm_access 用户确认授权页面 
> /oauth/error 错误界面 
> /oauth/check_token 解码token,获取token对应的全部信息 
> /oauth/token_key 公钥令牌验证, 如果使用JWT令牌
