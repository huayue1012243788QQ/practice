package com.huayue.job.service;

import com.huayue.common.enums.Check;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.UncheckException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.job.entity.Job;
import com.huayue.job.repository.CompanyRepository;
import com.huayue.job.repository.JobRepository;
import com.huayue.job.repository.JobTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Service
public class JobService extends BaseService<Job> {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobTypeRepository jobTypeRepository;
    @Override
    public BaseRepository<Job> getRepository() {
        return jobRepository;
    }

    public Job save(Job job) {
        if (!companyRepository.existsById(job.getCompanyId())) {
            throw new NotFoundException(job.getCompanyId());
        }
        if (companyRepository.findById(job.getCompanyId()).get().getChecked().equals(Check.UNCHECKED)) {
            throw new UncheckException(job.getCompanyId());
        }
        if (!jobTypeRepository.existsById(job.getJobTypeId())) {
            throw new NotFoundException(job.getJobTypeId());
        }
        return jobRepository.save(job);
    }

    public Job update(Job job) {
        if (!jobRepository.existsById(job.getId())) {
            throw new NotFoundException(job.getId());
        }
        if (!companyRepository.existsById(job.getCompanyId())) {
            throw new NotFoundException(job.getId());
        }
        if (!jobTypeRepository.existsById(job.getJobTypeId())) {
            throw new NotFoundException(job.getJobTypeId());
        }
        Job job1 = jobRepository.findById(job.getId()).get();
        BeanUtil.copyNonNullProperties(job,job1);
        return jobRepository.saveAndFlush(job1);
    }
    public void delete(String id) {
        if (!jobRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        jobRepository.delete(jobRepository.findById(id).get());
    }
}
