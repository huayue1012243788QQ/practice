package com.huayue.userservice.dto;

import lombok.Data;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/7.
 * @description
 */
@Data
public class JWT {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private String jti;
}
