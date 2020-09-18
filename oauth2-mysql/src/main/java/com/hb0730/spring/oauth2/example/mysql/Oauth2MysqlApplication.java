package com.hb0730.spring.oauth2.example.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mysql 存储令牌与客户端信息
 *
 * @author bing_huang
 */
@SpringBootApplication
public class Oauth2MysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2MysqlApplication.class, args);
    }
}
