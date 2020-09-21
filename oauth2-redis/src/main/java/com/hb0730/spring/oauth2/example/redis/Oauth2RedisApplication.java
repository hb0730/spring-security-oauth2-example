package com.hb0730.spring.oauth2.example.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * redis存储令牌
 *
 * @author bing_huang
 */
@SpringBootApplication
public class Oauth2RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2RedisApplication.class, args);
    }
}
