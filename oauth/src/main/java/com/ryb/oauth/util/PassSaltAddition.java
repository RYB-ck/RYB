package com.ryb.oauth.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author 常坤
 */
@Component
public class PassSaltAddition {
    public String passSaltAddition(@NotNull String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }

    public Boolean authPass(String pass, String hashedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(pass, hashedPassword);
    }
}
