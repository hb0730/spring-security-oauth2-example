# spring oauth2之刷新token

# 访问

1. 获取

http://localhost:8080/oauth/token?client_id=client&client_secret=secret&grant_type=password&username=user&password=password

2. 检查token
http://localhost:8080/oauth/check_token?token=

3. 刷新token
http://localhost/oauth/token?grant_type=refresh_token&refresh_token= &client_id=client&client_secret=secret
