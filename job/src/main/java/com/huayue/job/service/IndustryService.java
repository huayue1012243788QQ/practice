package com.huayue.job.service;

import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.job.entity.Industry;
import com.huayue.job.entity.JobType;
import com.huayue.job.repository.IndustryRepository;
import com.huayue.job.repository.JobTypeRepository;
import com.huayue.job.vo.IndustryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@Service
public class IndustryService extends BaseService<Industry> {
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private JobTypeRepository jobTypeRepository;

    @Override
    public BaseRepository<Industry> getRepository() {
        return industryRepository;
    }

    public Industry save(Industry industry) {
        if (industry.getParentId() != null) {
            if (!industryRepository.existsById(industry.getParentId())) {
                throw new NotFoundException(industry.getParentId());
            }
        }
        return industryRepository.saveAndFlush(industry);
    }

    public Industry update(Industry industry) {
        if (!industryRepository.existsById(industry.getId())) {
            throw new NotFoundException(industry.getId());
        }
        if (industryRepository.findByName(industry.getName()) != null) {
            if (!industryRepository.findByName(industry.getName()).getId().equals(industry.getId())) {
                throw new RepeatException();
            }
        }
        if (industry.getParentId() != null) {
            if (!industryRepository.existsByParentId(industry.getParentId())) {
                throw new NotFoundException(industry.getParentId());
            }
        }
        Industry industry1 = industryRepository.findById(industry.getId()).get();
        BeanUtil.copyNonNullProperties(industry,industry1);
        return industryRepository.saveAndFlush(industry1);
    }

    public void delete(String id) {
        if (!industryRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        List<Industry> industries = industryRepository.findByParentId(id);
        if (industries != null) {
            for (Industry industry:
                 industries) {
                List<JobType> jobTypes = jobTypeRepository.findByIndustryId(industry.getId());
                if (jobTypes != null) {
                    for (JobType jobType:
                         jobTypes) {
                        jobTypeRepository.delete(jobType);
                    }
                }
                industryRepository.delete(industry);
            }
        }
        industryRepository.delete(industryRepository.findById(id).get());
    }
    public List<IndustryVO> getIndustryVOAll() {
        List<Industry> industries = industryRepository.findAll();
        List<IndustryVO> industryVOS = new ArrayList<>();
        for (Industry industry:
             industries) {
            List<Industry> industryChildren = industryRepository.findByParentId(industry.getId());
            System.out.println(industry.getName());
            System.out.println(industryChildren.size());
            if (industryChildren.size() != 0) {
                for (Industry industryChild:
                     industryChildren) {
                    industryChild.setJobTypes(jobTypeRepository.findByIndustryId(industryChild.getId()));
                }
                IndustryVO industryVO = new IndustryVO(industry,industryChildren);
                industryVOS.add(industryVO);
            }
        }
        return industryVOS;
    }
}
