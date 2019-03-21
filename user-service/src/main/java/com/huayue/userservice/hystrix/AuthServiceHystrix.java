package com.huayue.userservice.hystrix;

import com.huayue.userservice.client.AuthServiceClient;
import com.huayue.userservice.dto.JWT;
import org.springframework.stereotype.Component;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/7.
 * @description
 */
@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
