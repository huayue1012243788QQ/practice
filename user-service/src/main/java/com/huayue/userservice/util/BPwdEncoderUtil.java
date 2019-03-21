package com.huayue.userservice.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/7.
 * @description
 */
public class BPwdEncoderUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String  BCryptPassword(String password){
        return encoder.encode(password);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword){
        return encoder.matches(rawPassword,encodedPassword);
    }
}
