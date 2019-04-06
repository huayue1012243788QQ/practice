package com.huayue.job.service;

import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.job.entity.JobType;
import com.huayue.job.repository.IndustryRepository;
import com.huayue.job.repository.JobTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@Service
public class JobTypeService extends BaseService<JobType> {
    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public BaseRepository<JobType> getRepository() {
        return jobTypeRepository;
    }

    public JobType save(JobType jobType) {
        if (!industryRepository.existsById(jobType.getIndustryId())) {
            throw new NotFoundException(jobType.getIndustryId());
        }
        return jobTypeRepository.saveAndFlush(jobType);
    }

    public JobType update(JobType jobType) {
        if (!jobTypeRepository.existsById(jobType.getId())) {
            throw new NotFoundException(jobType.getId());
        }
        if (jobTypeRepository.findByName(jobType.getName()) != null) {
            if (jobTypeRepository.findByName(jobType.getName()).getId().equals(jobType.getId())) {
                throw new RepeatException();
            }
        }
        if (!industryRepository.existsById(jobType.getIndustryId())) {
            throw new NotFoundException(jobType.getIndustryId());
        }
        JobType jobType1 = jobTypeRepository.findById(jobType.getId()).get();
        BeanUtil.copyNonNullProperties(jobType, jobType1);
        return jobTypeRepository.saveAndFlush(jobType1);
    }

    public void delete(String id) {
        if (!jobTypeRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        JobType jobType = jobTypeRepository.findById(id).get();
        jobTypeRepository.delete(jobType);
    }
}
