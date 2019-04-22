package com.huayue.job.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.CheckRepeatException;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.UncheckException;
import com.huayue.common.global.Result;
import com.huayue.job.entity.Job;
import com.huayue.job.service.JobService;
import com.huayue.job.vo.JobVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@RestController
@RequestMapping("job")
@Api(tags = {"岗位相关接口"})
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/")
    public Object getAll() {
        return Result.success(jobService.queryForAll());
    }

    @PostMapping("/")
    public Object addOne(@RequestBody Job job) {
        if (StringUtils.isEmpty(job.getTitle()) || StringUtils.isEmpty(job.getCompanyId())) {
            return Result.failure(MessageConst.VALUE_NULL_MESSAGE);
        }
        Job job1;
        try {
            job1 = jobService.save(job);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (UncheckException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (EnumErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(job1);
    }
    @PutMapping("/")
    public Object updateOne(@RequestBody Job job) {
        if (StringUtils.isEmpty(job.getTitle()) || StringUtils.isEmpty(job.getCompanyId())) {
            return Result.failure(MessageConst.VALUE_NULL_MESSAGE);
        }
        Job job1;
        try {
            job1 = jobService.update(job);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (EnumErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(job1);
    }
    @DeleteMapping("/{id}")
    public Object deleteOne(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        try {
            jobService.delete(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());

        }
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
    @GetMapping("/{id}")
    public Object getOne(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        JobVO jobVO;
        try {
            jobVO = jobService.findOneById(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(jobVO);
    }
    @GetMapping("/list")
    public Object queryForList(@RequestParam(required = false) String title,
                               @RequestParam(required = false) Integer minSalary,
                               @RequestParam(required = false) Integer maxSalary,
                               @RequestParam(required = false) Integer workDay,
                               @RequestParam(required = false) Integer workTime,
                               @RequestParam(required = false) String educationRank,
                               @RequestParam(required = false) String city,
                               @RequestParam(required = false) String jobTypeId,
                               @RequestParam Integer page,
                               @RequestParam Integer size) {
        page -= 1;
        if (size == 0) {
            size =1;
        }
        Page<Job> jobs = jobService.queryForList(title,jobTypeId,minSalary,maxSalary,workDay,workTime,educationRank,city,page,size);
        return Result.success(jobs);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_REVIEWER')")
    @PatchMapping("/{id}")
    public Object checkJob(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        Job job;
        try {
            job = jobService.check(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (CheckRepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(job);
    }
    @GetMapping("/getUncheckJobList")
    public Object getUncheckList() {
        return Result.success(jobService.getUncheckList());
    }
    @GetMapping("/getList")
    public Object getList(@RequestParam int page, @RequestParam int size) {
        page -= 1;
        return Result.success(jobService.getList(page,size));
    }
}
