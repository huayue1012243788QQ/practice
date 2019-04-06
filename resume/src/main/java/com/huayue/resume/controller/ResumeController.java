package com.huayue.resume.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.global.Result;
import com.huayue.resume.entity.Resume;
import com.huayue.resume.service.ResumeService;
import com.huayue.resume.vo.ResumeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 */
@RestController
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
    @GetMapping("/getByPersonInfoId")
    public Object getByPersonInfoId(@RequestParam String personInfoId) {
        if (StringUtils.isEmpty(personInfoId)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        List<Resume> resumes;
        try {
            resumes = resumeService.getByPersonInfoId(personInfoId);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(resumes);
    }
    @GetMapping("/getResumeVOByResumeId")
    public Object getResumeVOByResumeId(@RequestParam String resumeId) {
        if (StringUtils.isEmpty(resumeId)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        ResumeVO resumeVO;
        try {
            resumeVO = resumeService.getResumeVOByResumeId(resumeId);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(resumeVO);
    }
    @DeleteMapping("/{id}")
    public Object deleteOne(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        try {
            resumeService.delete(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
}
