package com.huayue.adminservice.service;

import com.huayue.adminservice.client.JobServiceClient;
import com.huayue.adminservice.entity.CheckResult;
import com.huayue.adminservice.repository.CheckResultRepository;
import com.huayue.common.enums.check.CheckResultEnum;
import com.huayue.common.enums.check.CheckType;
import com.huayue.common.exception.CheckErrorException;
import com.huayue.common.exception.CheckRepeatException;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description
 */
@Service
public class CheckResultService extends BaseService<CheckResult> {
    @Autowired
    private CheckResultRepository checkResultRepository;
    @Autowired
    private JobServiceClient jobServiceClient;
    @Override
    public BaseRepository<CheckResult> getRepository() {
        return checkResultRepository;
    }
    public CheckResult save(CheckResult checkResult) {
        if (checkResultRepository.existsByCheckedId(checkResult.getCheckedId())) {
            throw new CheckRepeatException();
        }
        if (CheckType.check(checkResult.getCheckType()) == false || CheckResultEnum.check(checkResult.getCheckResult()) == false) {
            throw new EnumErrorException();
        }
        if (checkResult.getCheckType().equals(CheckType.COMPANY_INFO_CHECK.toString())) {
            if (jobServiceClient.getCompany(checkResult.getCheckedId()).getCode() != 200) {
                throw new NotFoundException(checkResult.getCheckedId());
            }
            if (jobServiceClient.checkCompany(checkResult.getCheckedId()).getCode() != 200) {
                throw new CheckErrorException();
            }
        } else if (checkResult.getCheckType().equals(CheckType.JOB_INFO_CHECK.toString())) {
            if (jobServiceClient.getJOb(checkResult.getCheckedId()).getCode() != 200) {
                throw new NotFoundException(checkResult.getCheckedId());
            }
            if (jobServiceClient.checkJob(checkResult.getCheckedId()).getCode() != 200) {
                throw new CheckErrorException();
            }
        }
        return checkResultRepository.save(checkResult);
    }
}
