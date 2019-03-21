package com.huayue.job.controller;

import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.CheckRepeatException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import com.huayue.job.entity.Company;
import com.huayue.job.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"公司相关接口"} )
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @ApiOperation(value = "获取全部公司信息的列表。由管理员进行操作。")
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_COMPANY')")
    public Object getAll() {
        return Result.success(companyService.queryForAll());
    }
    @PostMapping("/")
    public Object addOne(@RequestBody Company company) {
        if (StringUtils.isEmpty(company.getName())) {
            return Result.failure(MessageConst.VALUE_NULL_MESSAGE);
        }
        Company company1;
        try {
            company1 = companyService.save(company);
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(company1);
    }
    @PutMapping("/")
    public Object update(@RequestBody Company company) {
        if (StringUtils.isEmpty(company.getName())) {
            return Result.failure(MessageConst.VALUE_NULL_MESSAGE);
        }
        Company company1;
        try {
            company1 = companyService.update(company);
        } catch (RepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(company1);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public Object deleteOne(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        try {
            companyService.delete(id);
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
        Company company;
        try {
            company = companyService.findById(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(company);
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_REVIEWER')")
    public Object checkCompany(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.failure(MessageConst.ID_NULL_MESSAGE);
        }
        Company company;
        try {
            company = companyService.check(id);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (CheckRepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(company);
    }
}
