package com.huayue.userservice.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import com.huayue.userservice.entity.Role;
import com.huayue.userservice.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/12.
 * @description
 */
@RestController
@Slf4j
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object addOne(@RequestBody Role role) {
        Role role1;
        try {
            role1 = roleService.save(role);
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        }
        return Result.success(role1);
    }
    @PutMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object updateOne(@RequestBody Role role) {
        Role role1;
        try {
            role1 = roleService.update(role);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        }
        return Result.success(role1);
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object getALL() {
        return Result.success(roleService.queryForAll());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object deleteOne(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        try {
            roleService.delete(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        }
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
    @GetMapping("/user-role")
    public Object getUserRole() {
        return Result.success(roleService.getUserRole());
    }
}
