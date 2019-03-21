package com.huayue.userservice.dto;

import com.huayue.userservice.entity.User;
import lombok.Data;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/7.
 * @description
 */
@Data
public class UserLoginDTO {
    private JWT jwt;
    private User user;
}
