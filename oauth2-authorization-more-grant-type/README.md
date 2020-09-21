# oauth2之自定义授权方式(sms,mail)

## 添加自定义端点

## 添加授权模式


## 请求方式
1. 获取验证码
GET: http://localhost:8080/code/sms?sms=13712341234
2. 获取令牌
POST: http://localhost:8080/oauth/token?grant_type=sms&client_id=oauth2&code=验证码&sms=13712341234&scope=all
