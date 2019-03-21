package com.huayue.resume.service;

import com.huayue.common.enums.ResumeType;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.resume.entity.Resume;
import com.huayue.resume.repository.PersonInfoRepository;
import com.huayue.resume.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/15.
 */
@Service
public class ResumeService extends BaseService<Resume> {
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private PersonInfoRepository personInfoRepository;
    @Override
    public BaseRepository<Resume> getRepository() {
        return resumeRepository;
    }
    public Resume save(Resume resume) {
        if (!ResumeType.contains(resume.getResumeType())) {
            throw new EnumErrorException();
        }
        if (!personInfoRepository.existsById(resume.getPersonInfoId())) {
            throw new NotFoundException(resume.getPersonInfoId());
        }
        return resumeRepository.saveAndFlush(resume);
    }
    public Resume update(Resume resume) {
        if (!ResumeType.contains(resume.getResumeType())) {
            throw new EnumErrorException();
        }
        if (!personInfoRepository.existsById(resume.getPersonInfoId())) {
            throw new NotFoundException(resume.getPersonInfoId());
        }
        if (!resumeRepository.existsById(resume.getId())) {
            throw new NotFoundException(resume.getId());
        }
        Resume resume1 = resumeRepository.findById(resume.getId()).get();
        BeanUtil.copyNonNullProperties(resume,resume1);
        return resumeRepository.saveAndFlush(resume1);
    }
}
