package com.huayue.job.service;

import com.huayue.common.enums.EducationRank;
import com.huayue.common.enums.check.Check;
import com.huayue.common.exception.CheckRepeatException;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.UncheckException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.job.entity.Company;
import com.huayue.job.entity.Industry;
import com.huayue.job.entity.Job;
import com.huayue.job.entity.JobType;
import com.huayue.job.repository.CompanyRepository;
import com.huayue.job.repository.IndustryRepository;
import com.huayue.job.repository.JobRepository;
import com.huayue.job.repository.JobTypeRepository;
import com.huayue.job.vo.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private IndustryRepository industryRepository;
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
        if (!EducationRank.check(job.getEducationRank())) {
            throw new EnumErrorException();
        }
        job.setChecked(Check.UNCHECKED.toString());
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
        if (!EducationRank.check(job.getEducationRank())) {
            throw new EnumErrorException();
        }
        Job job1 = jobRepository.findById(job.getId()).get();
        BeanUtil.copyNonNullProperties(job,job1);
        job1.setChecked(Check.UNCHECKED.toString());
        return jobRepository.saveAndFlush(job1);
    }
    public void delete(String id) {
        if (!jobRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        jobRepository.delete(jobRepository.findById(id).get());
    }
    public Page<Job> queryForList(String title,
                                  Integer minSalary,
                                  Integer maxSalary,
                                  Integer workDay,
                                  Integer workTime,
                                  String educationRank,
                                  String city,
                                  Integer page,
                                  Integer size) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC,"id");
        Page<Job> jobs = jobRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(title)) {
                predicates.add(criteriaBuilder.like(root.get("title").as(String.class),"%" + title +"%"));
            }
            if (minSalary != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("minSalary").as(int.class),minSalary));
            }
            if (maxSalary != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("maxSalary").as(int.class),maxSalary));
            }
            if (workDay != null) {
                predicates.add(criteriaBuilder.equal(root.get("workDay").as(int.class),workDay));
            }
            if (workTime != null) {
                predicates.add(criteriaBuilder.equal(root.get("workTime").as(int.class),workTime));
            }
            if (!StringUtils.isEmpty(educationRank) && EducationRank.check(educationRank)) {
                predicates.add(criteriaBuilder.equal(root.get("educationRank").as(String.class),educationRank));
            }
            if (!StringUtils.isEmpty(city)) {
                predicates.add(criteriaBuilder.equal(root.get("city").as(String.class),city));
            }
            predicates.add(criteriaBuilder.equal(root.get("checked").as(String.class),Check.CHECKED.toString()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        for (Job job:
             jobs.getContent()) {
            Company company = companyRepository.findById(job.getCompanyId()).get();
            company.setIndustry(industryRepository.findById(company.getIndustryId()).get());
            job.setCompany(company);
        }
        return jobs;
    }
    public Job check(String id) {
        if (!jobRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        Job job = jobRepository.findById(id).get();
        if (job.getChecked().equals(Check.CHECKED)) {
            throw new CheckRepeatException();
        }
        job.setChecked(Check.CHECKED.toString());
        return jobRepository.saveAndFlush(job);
    }
    public JobVO findOneById(String id) {
        if (!jobRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        Job job = jobRepository.findById(id).get();
        Company company = companyRepository.findById(job.getCompanyId()).get();
        Industry industry = industryRepository.findById(company.getIndustryId()).get();
        JobType jobType = jobTypeRepository.findById(job.getJobTypeId()).get();
        return new JobVO(job,company,jobType,industry);
    }
}
