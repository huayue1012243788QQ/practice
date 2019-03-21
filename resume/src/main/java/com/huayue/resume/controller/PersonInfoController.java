package com.huayue.resume.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import com.huayue.resume.entity.PersonInfo;
import com.huayue.resume.service.PersonInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@RestController
@Slf4j
@RequestMapping("person-info")
public class PersonInfoController {
    @Autowired
    private PersonInfoService personInfoService;
    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER','ROLE_STU')")
    public Object addOne(@RequestBody PersonInfo personInfo) {
        PersonInfo personInfo1;
        try {
            personInfo1 = personInfoService.save(personInfo);
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(personInfo1);
    }
    @GetMapping("/")
    public Object getAll() {
        return Result.success(personInfoService.queryForAll());
    }
    @PutMapping("/")
    public Object updateOne(@RequestBody PersonInfo personInfo) {
        if (StringUtils.isEmpty(personInfo.getId())) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        PersonInfo personInfo1;
        try {
            personInfo1 = personInfoService.update(personInfo);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(personInfo1);
    }
}
