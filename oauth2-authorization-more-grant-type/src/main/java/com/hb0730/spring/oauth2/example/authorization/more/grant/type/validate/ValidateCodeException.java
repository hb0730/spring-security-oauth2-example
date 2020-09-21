package com.hb0730.spring.oauth2.example.authorization.more.grant.type.validate;

/**
 * @author bing_huang
 */
public class ValidateCodeException extends RuntimeException{

    public ValidateCodeException(String message) {
        super(message);
    }
}
