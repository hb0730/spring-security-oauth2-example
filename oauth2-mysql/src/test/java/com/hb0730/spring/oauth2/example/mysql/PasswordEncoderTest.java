package com.hb0730.spring.oauth2.example.mysql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author bing_huang
 */
@Slf4j
public class PasswordEncoderTest {
    @Test
    public void test() {
        log.info(new BCryptPasswordEncoder().encode("secret"));

    }
}
