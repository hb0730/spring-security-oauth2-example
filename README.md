# spring-security-oauth2-example
spring security oauth2 demo(security oauth2案例)

|项目|说明|
|----|----|
|oauth2-password|密码模式|
|oauth2-implicit|简单模式|
|oauth2-authorization-code|授权码模式|
|oauth2-client_credentials|客户端模式|
|oauth2-refresh-token|刷新token|
|oauth2-auth-resource|	综合测试, 认证服务器,资源服务器独立允许,授权码,密码,刷新token|
|oauth2-page|自定义login页面|
|oauth2-mysql|jdbc存储令牌以及存储客户端信息|
|oauth2-redis|redis存储令牌信息|
|oauth2-authorization-expansion|授权服务器扩展(自定义授权页面与登录页面等)|
|oauth2-more-grantType|自定义授权(email,sms等)|

# 客户端加密
在 spring security oauth 中，推荐加密我们的客户端信息，客户端和授权服务器建立适合授权服务器安全要求的客户端认证方法。授权服务器可以接受满足其安全要求的任何形式的客户端身份验证。一般来说我们使用的是 **密码验证** 的方式加密我们的客户端信息。

推荐的方式是使用 HTTP Basic ，我们需要设置以下参数，当设置成功以后将客户端凭证加密存放在请求头中去请求授权信息，参数如下：

|参数名称|是否必填|描述|
|----|----|----|
|client_id|REQUIRED|客户端 id|
|client_secret|REQUIRED|客户端密码，如果客户机secret是空字符串，则客户机可以省略该参数|

# spring oauth2 端点
## 授权端点(授权码模式)
* /oauth/authorize: 授权端点，通过此端点跳转到 **授权服务器** 进行认证，完成第一个请求。携带如下参数：

|参数名称|是否必填|描述|
|----|----|----|
|response_type|REQUIRED|必须为code|
|client_id|REQUIRED|客户端id|
|redirect_uri|OPTIONAL|重定向地址|
|scope|OPTIONAL|申请的权限范围|
|state|RECOMMENDED|客户端的当前状态，可以指定任意值，认证服务器会原封不动地返回这个值，推荐。|

授权成功的情况，会携带以下两个参数重定向到到 **redirect_uri**中：

|参数名称|是否必有|描述|
|----|----|----|
|code|REQUIRED|授权服务器生成的授权代码。授权代码必须在发布后不久过期，以降低泄漏的风险。最大授权代码生命周期为10分钟|
|state|REQUIRED|如果上一步中提供 `state` 参数，会原封不动地返回这个值。|

### 令牌端点
* /oauth/token：令牌端点，通过上一步获取到的 授权码 验证与生成令牌，完成第二个请求，携带如下参数：


|参数名称|是否必填|描述|
|----|----|----|
|grant_type|REQUIRED|使用的授权模式，值固定为"authorization_code"|
|code|REQUIRED|上一步获得的授权码|
|redirect_uri|REQUIRED|重定向URI，必须与上一步中的该参数值保持一致|
|client_id|REQUIRED|客户端的id|
|scope|RECOMMENDED|授权范围，必须与第一步相同|

如果访问令牌请求有效且经过授权，授权服务器将发出访问令牌和可选的刷新令牌，可以得到如下响应参数：


|参数名称|是否必有|描述|是否有实现|
|----|----|----|----|
|access_token|REQUIRED|授权服务器颁发的访问令牌|是|
|token_type|REQUIRED|令牌类型，该值大小写不敏感，可以是bearer类型或mac类型|是|
|expires_in|RECOMMENDED|过期时间，单位为秒|是|
|refresh_token|OPTIONAL|表示更新令牌，用来获取下一次的访问令牌|是，需要设置|
|scope|OPTIONAL|权限范围，如果有，则与客户端申请的范围一致|是|

## 密码模式
### 令牌端点
* /oauth/token：令牌端点，携带如下参数请求即可：

|请求参数|是否必填|描述|
|----|----|----|
|grant_type|REQUIRED|使用的密码模式，值固定为"password"|
|username|REQUIRED|用户名|
|password|REQUIRED|密码|
|scope|OPTIONAL|请求权限范围|

# 安全考虑
> **注意**：以下所有请求都必须在请求头中携带上面所说的客户端加密信息！

作为一个灵活且可扩展的框架，OAuth 的安全考虑取决于许多因素。spring security oauth 为我们提供了一些默认的端点如下：

* /oauth/authorize：授权端点
* /oauth/token：令牌端点
* /oauth/token：令牌端点也同时拥有刷新用户的功能，请求参数如下：

|参数名称|是否必填|描述|
|----|----|----|
|grant_type|REQUIRED|固定值为“refresh_token”|
|refresh_token|REQUIRED|请求到 token 时传过来的 refresh_token|

* /oauth/confirm_access：用户确认授权提交端点
* /oauth/error：授权服务错误信息端点
* /oauth/check_token：用于资源服务访问的令牌解析端点，请求参数如下：

|参数名称|是否必填|描述|
|----|----|----|
|token|REQUIRED|得到的有效的令牌|

* /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话

# 注意
部分项目来自[知一码园](https://zhiyi.zone/) , [啊月很乖](https://echocow.cn/)

# thanks
* [知一码园](https://zhiyi.zone/)
* [啊月很乖](https://echocow.cn/)
