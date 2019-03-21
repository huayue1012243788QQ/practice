package com.huayue.applyservice.controller;

import com.huayue.applyservice.entity.Apply;
import com.huayue.applyservice.service.ApplyService;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/20.
 * @description
 */
@RestController
@Slf4j
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @PreAuthorize("hasAuthority('ROLE_STU')")
    @PostMapping("/")
    public Object addOne(@RequestBody Apply apply) {
        Apply apply1;
        try {
            apply1 = applyService.save(apply);
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(apply1);
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object getAll() {
        return applyService.queryForAll();
    }
}
