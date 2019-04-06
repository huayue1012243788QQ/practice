package com.huayue.job.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import com.huayue.job.entity.JobType;
import com.huayue.job.service.JobTypeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@RestController
@Slf4j
@RequestMapping("job-type")
@Api(tags = {"岗位种类相关接口"})
public class JobTypeController {
    @Autowired
    private JobTypeService jobTypeService;

    @GetMapping("/")
    public Object getAll() {
        return Result.success(jobTypeService.queryForAll());
    }

    @PostMapping("/")
    public Object addOne(@RequestBody JobType jobType) {
        JobType jobType1;
        try {
            jobType1 = jobTypeService.save(jobType);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        }
        return Result.success(jobType1);
    }

    @PutMapping("/")
    public Object updateOne(@RequestBody JobType jobType) {
        JobType jobType1;
        try {
            jobType1 = jobTypeService.update(jobType);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        }
        return Result.success(jobType1);
    }

    @DeleteMapping("/{id}")
    public Object deleteOne(@PathVariable String id) {
        if (id == null || id.equals("")) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        try {
            jobTypeService.delete(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        }
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
}
