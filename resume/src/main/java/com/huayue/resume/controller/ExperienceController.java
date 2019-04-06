package com.huayue.resume.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.global.Result;
import com.huayue.resume.entity.Experience;
import com.huayue.resume.service.ExperienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/4/6.
 * @description
 */
@RequestMapping("experience")
@RestController
@Slf4j
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;
    @PostMapping("/")
    public Object addOne(@RequestBody Experience experience) {
        return Result.success(experienceService.save(experience));
    }
    @PutMapping("/")
    public Object updateOne(@RequestBody Experience experience) {
        return Result.success(experienceService.update(experience));
    }
    @DeleteMapping("/{id}")
    public Object deleteOne(@PathVariable String id) {
        experienceService.delete(id);
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
}
