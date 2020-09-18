# spring oauth2 之jdbc存储

# 初始化SQL
`https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql`

`
由于我们使用的是 MySQL 数据库，默认建表语句中主键为 VARCHAR(256)，这超过了最大的主键长度，请手动修改为 128，并用 BLOB 替换语句中的 LONGVARBINARY 类型，修改后的建表脚本如下：
`

# 访问
## 未新增客户端
1. http://localhost:8080

## 新增客户端
```sql
INSERT INTO `oauth2-test`.`oauth_client_details`(`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client', NULL, '$2a$10$jEOkxbXHguVjkcnLteFrn.FmbtlWu5OHk2DNuWLyDS1vDXRpbld4u', 'all', 'password,authorization_code', 'http://localhost:8080/index', NULL, NULL, NULL, NULL, NULL);

```
1. code: http://localhost:8080/oauth/authorize?client_id=client&response_type=code

2. postman: curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=1JuO6V' "http://client:secret@localhost:8080/oauth/token"

<img src="https://github.com/hb0730/spring-security-oauth2-example/blob/master/doc/jdbc/jdbc-01.png" alt="1">

3. 数据库 `oauth_access_token` 表中会增加一笔记录
