package com.huayue.adminservice.controller;

import com.huayue.adminservice.entity.CheckResult;
import com.huayue.adminservice.service.CheckResultService;
import com.huayue.common.constant.MessageConst;
import com.huayue.common.exception.CheckErrorException;
import com.huayue.common.exception.CheckRepeatException;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.global.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description
 */
@RestController
public class CheckResultController {
    @Autowired
    private CheckResultService checkResultService;
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_REVIEWER')")
    @PostMapping("/")
    public Object addOne(@RequestBody CheckResult checkResult) {
        if (StringUtils.isEmpty(checkResult.getCheckedId())
                || StringUtils.isEmpty(checkResult.getCheckResult())
                || StringUtils.isEmpty(checkResult.getCheckType())
                || StringUtils.isEmpty(checkResult.getRemark())) {
            return Result.failure(MessageConst.VALUE_NULL_MESSAGE);
        }
        CheckResult checkResult1;
        try {
            checkResult1 = checkResultService.save(checkResult);
        } catch (NotFoundException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (EnumErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (CheckErrorException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        } catch (CheckRepeatException e) {
            return Result.failure(e.getRetCd(),e.getMsgDes());
        }
        return Result.success(checkResult1);
    }
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_REVIEWER')")
    public Object getAll() {
        return Result.success(checkResultService.queryForAll());
    }
}
