package com.huayue.userservice.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.PasswordErrorException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.exception.TokenNullException;
import com.huayue.common.global.Result;
import com.huayue.userservice.dto.LoginDTO;
import com.huayue.userservice.dto.UserLoginDTO;
import com.huayue.userservice.entity.Role;
import com.huayue.userservice.entity.User;
import com.huayue.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/7.
 * @description
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public Object addOne(@RequestBody User user,@RequestParam String userType) {
        User user1;
        try {
            user1 = userService.save(user,userType);
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(user1);
    }
    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO loginDTO) {
        UserLoginDTO userLoginDTO;
        try {
            userLoginDTO = userService.login(loginDTO);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (PasswordErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (TokenNullException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(userLoginDTO);
    }
    @PutMapping("/")
    public Object updateOne(@RequestBody User user) {
        User user1;
        try {
            user1 = userService.update(user);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(user1);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object deleteOne(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        try {
            userService.delete(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object getAll() {
        return Result.success(userService.queryForAll());
    }
    @PatchMapping("/role/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object changeAuthority(@PathVariable String id, @RequestBody List<Role> roles) {
        User user;
        try {
            user = userService.changeAuthority(id,roles);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(user);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN','ROLE_COMPANY')")
    @GetMapping("/{id}")
    public Object getOne(@PathVariable("id") String id) {
        User user;
        try {
            user = userService.findById(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(user);
    }
    @GetMapping("/principal")
    public Principal user(Principal user){
        return user;
    }
    @GetMapping("/getByUsername")
    public Object getByUsername(@RequestParam String username) {
        if (StringUtils.isEmpty(username)) {
            return Result.failure(MessageConst.VALUE_NULL_MESSAGE);
        }
        User user;
        try {
            user = (User) userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            return Result.failure(e.getMessage());
        }
        return Result.success(user);
    }
}
