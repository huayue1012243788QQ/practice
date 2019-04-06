package com.huayue.resume.service;

import com.huayue.common.enums.EducationRank;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.resume.entity.Education;
import com.huayue.resume.repository.EducationRepository;
import com.huayue.resume.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 */
@Service
public class EducationService extends BaseService<Education> {
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ResumeRepository resumeRepository;
    @Override
    public BaseRepository<Education> getRepository() {
        return educationRepository;
    }
    public Education save(Education education) {
        if (!resumeRepository.existsById(education.getResumeId())) {
            throw new NotFoundException(education.getResumeId());
        }
        if (!EducationRank.contains(education.getEducationRank().toString())) {
            throw new EnumErrorException();
        }
        return educationRepository.save(education);
    }
    public Education update(Education education) {
        if (!educationRepository.existsById(education.getId())) {
            throw new NotFoundException(education.getId());
        }
        if (!resumeRepository.existsById(education.getResumeId())) {
            throw new NotFoundException(education.getResumeId());
        }
        if (!EducationRank.contains(education.getEducationRank().toString())) {
            throw new EnumErrorException();
        }
        Education education1 = educationRepository.findById(education.getId()).get();
        BeanUtil.copyNonNullProperties(education,education1);
        return educationRepository.saveAndFlush(education1);
    }
    public void delete(String id) {
        if (!educationRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        educationRepository.deleteById(id);
    }
}
