package com.huayue.resume.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.global.Result;
import com.huayue.resume.entity.Education;
import com.huayue.resume.service.EducationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/25.
 * @description
 */
@RestController
@Slf4j
@RequestMapping("education")
public class EducationController {
    @Autowired
    private EducationService educationService;
    @PostMapping("/")
    public Object addOne(@RequestBody Education education) {
        Education education1;
        try {
            education1 = educationService.save(education);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (EnumErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(education1);
    }
    @PutMapping("/")
    public Object updateOne(@RequestBody Education education) {
        Education education1;
        try {
            education1 = educationService.update(education);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (EnumErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(education1);
    }
    @DeleteMapping("/{id}")
    public Object deleteOne(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        try {
            educationService.delete(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
}
