package com.huayue.resume.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.global.Result;
import com.huayue.resume.entity.Resume;
import com.huayue.resume.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 */
@RestController
@RequestMapping("resume")
@Slf4j
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/")
    public Object addOne(@RequestBody Resume resume) {
        Resume resume1;
        try {
            resume1 = resumeService.save(resume);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (EnumErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(resume1);
    }
    @PutMapping("/")
    public Object updateOne(@RequestBody Resume resume) {
        Resume resume1;
        try {
            resume1 = resumeService.update(resume);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (EnumErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(resume1);
    }
    @GetMapping("/")
    public Object getAll() {
        return Result.success(resumeService.queryForAll());
    }
    @PreAuthorize("hasAuthority('ROLE_STU')")
    @GetMapping("/{id}")
    public Object getOne(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        Resume resume;
        try {
            resume = resumeService.findById(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(resume);
    }
}
