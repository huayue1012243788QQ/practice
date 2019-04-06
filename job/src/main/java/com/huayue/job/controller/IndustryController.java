package com.huayue.job.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import com.huayue.job.entity.Industry;
import com.huayue.job.service.IndustryService;
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
@RequestMapping("industry")
@Api(tags = {"行业相关接口"})
public class IndustryController {
    @Autowired
    private IndustryService industryService;

    @PostMapping("/")
    public Object addOne(@RequestBody Industry industry) {
        Industry industry1;
        try {
            industry1 = industryService.save(industry);
        }  catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(industry1);
    }

    @PutMapping("/")
    public Object updateOne(@RequestBody Industry industry) {
        Industry industry1;
        try {
            industry1 = industryService.update(industry);
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(), e.getMsgDes());
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(industry1);
    }
    @GetMapping("/")
    public Object getAll() {
        return Result.success(industryService.getIndustryVOAll());
    }

    @DeleteMapping("/{id}")
    public Object deleteOne(@PathVariable String id) {
        if (id == null || id.equals("")) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        try {
            industryService.delete(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(MessageConst.DELETE_SUCCESS_MESSAGE);
    }
}
