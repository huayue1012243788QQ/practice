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
import com.huayue.job.entity.Industry;
import com.huayue.job.entity.Job;
import com.huayue.job.repository.CompanyRepository;
import com.huayue.job.repository.IndustryRepository;
import com.huayue.job.repository.JobRepository;
import com.huayue.job.repository.JobTypeRepository;
import com.huayue.job.vo.CompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private JobTypeRepository jobTypeRepository;
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
    public CompanyVO findOneById(String id) {
        if (!companyRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        Company company = companyRepository.findById(id).get();
        List<Job> jobs = jobRepository.findByCompanyId(id);
        for (Job job:
             jobs) {
            job.setJobType(jobTypeRepository.findById(job.getJobTypeId()).get());
        }
        Industry industry = industryRepository.findById(company.getIndustryId()).get();
        CompanyVO companyVO = new CompanyVO(company,jobs,industry);
        return companyVO;
    }
    public CompanyVO findByUserId(String id) {
        if (!companyRepository.existsByUserId(id)) {
            throw new NotFoundException(id);
        }
        Company company = companyRepository.findByUserId(id).get(0);
        List<Job> jobs = jobRepository.findByCompanyId(company.getId());
        for (Job job:
                jobs) {
            job.setJobType(jobTypeRepository.findById(job.getJobTypeId()).get());
        }
        Industry industry = industryRepository.findById(company.getIndustryId()).get();
        industry.setIndustry(industryRepository.findById(industry.getParentId()).get());
        CompanyVO companyVO = new CompanyVO(company,jobs,industry);
        return companyVO;
    }
    public List<CompanyVO> getUncheckList() {
        List<Company> companies = companyRepository.findByChecked(Check.UNCHECKED.toString());
        List<CompanyVO> companyVOS = new ArrayList<>();
        for (Company company:
             companies) {
            Industry industry = industryRepository.findById(company.getIndustryId()).get();
            CompanyVO companyVO = new CompanyVO(company,null,industry);
            companyVOS.add(companyVO);
        }
        return companyVOS;
    }
    public Page<Company> queryForList(int page,int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC,"id");
        Page<Company> companies = companyRepository.findAll(pageable);
        for (Company company:
             companies.getContent()) {
            Industry industry = industryRepository.findById(company.getIndustryId()).get();
            company.setIndustry(industry);
        }
        return companies;
    }
}
