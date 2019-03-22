package com.huayue.job.service;

import com.huayue.common.enums.check.Check;
import com.huayue.common.exception.CheckRepeatException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.global.Result;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.job.client.UserServiceClient;
import com.huayue.job.entity.Company;
import com.huayue.job.entity.Job;
import com.huayue.job.repository.CompanyRepository;
import com.huayue.job.repository.IndustryRepository;
import com.huayue.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Service
public class CompanyService extends BaseService<Company> {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserServiceClient userServiceClient;
    @Override
    public BaseRepository<Company> getRepository() {
        return companyRepository;
    }
    public Company save(Company company) {
        if (!industryRepository.existsById(company.getIndustryId())) {
            throw new NotFoundException(company.getIndustryId());
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new RepeatException();
        }
        Result result = userServiceClient.getOne(company.getUserId());
        if (result.getCode() != Result.SUCCESS) {
            throw new NotFoundException(company.getUserId());
        }
        company.setChecked(Check.UNCHECKED.toString());
        return companyRepository.saveAndFlush(company);
    }
    public Company update(Company company) {
        if (!companyRepository.existsById(company.getId())) {
            throw new NotFoundException(company.getId());
        }
        if (!industryRepository.existsById(company.getIndustryId())) {
            throw new NotFoundException(company.getIndustryId());
        }
        if (companyRepository.findByName(company.getName()) != null) {
            if (!companyRepository.findByName(company.getName()).getId().equals(company.getId())) {
                throw new RepeatException();
            }
        }
        Company company1 = companyRepository.findById(company.getId()).get();
        BeanUtil.copyNonNullProperties(company,company1);
        company1.setChecked(Check.UNCHECKED.toString());
        return companyRepository.saveAndFlush(company1);
    }
    public void delete(String id) {
        if (!companyRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        List<Job> jobs = jobRepository.findByCompanyId(id);
        if (jobs != null) {
            for (Job job:
                 jobs) {
                jobRepository.delete(job);
            }
        }
        companyRepository.delete(companyRepository.findById(id).get());
    }
    public Company check(String id) {
        if (!companyRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        Company company = companyRepository.findById(id).get();
        if (company.getChecked().equals(Check.CHECKED)) {
            throw new CheckRepeatException();
        }
        company.setChecked(Check.CHECKED.toString());
        return companyRepository.saveAndFlush(company);
    }
}
