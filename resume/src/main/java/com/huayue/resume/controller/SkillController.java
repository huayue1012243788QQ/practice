package com.huayue.resume.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.global.Result;
import com.huayue.resume.entity.Skill;
import com.huayue.resume.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/4/6.
 * @description
 */
@RestController
@Slf4j
@RequestMapping("skill")
public class SkillController {
    @Autowired
    private SkillService skillService;
    @PostMapping("/")
    public Object addOne(@RequestBody Skill skill) {
        return Result.success(skillService.save(skill));
    }
    @PutMapping("/")
    public Object updateOne(@RequestBody Skill skill) {
        return Result.success(skillService.update(skill));
    }
    @DeleteMapping("/{id}")
    public Object deleteOne(@PathVariable String id) {
        skillService.delete(id);
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
}
