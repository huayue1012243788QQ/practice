package com.huayue.resume.service;

import com.huayue.common.enums.ResumeType;
import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.exception.NotFoundException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.resume.entity.*;
import com.huayue.resume.repository.*;
import com.huayue.resume.vo.ResumeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private EducationRepository educationRepository;
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
        System.out.println(resume.getResumeType());
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
    public List<Resume> getByPersonInfoId(String personInfoId) {
        if (!personInfoRepository.existsById(personInfoId)) {
            throw new NotFoundException(personInfoId);
        }
        List<Resume> resumes = resumeRepository.findByPersonInfoId(personInfoId);
        return resumes;
    }
    public ResumeVO getResumeVOByResumeId(String resumeId) {
        if (!resumeRepository.existsById(resumeId)) {
            throw new NotFoundException(resumeId);
        }
        Resume resume = resumeRepository.findById(resumeId).get();
        PersonInfo personInfo = personInfoRepository.findById(resume.getPersonInfoId()).get();
        List<Skill> skills = skillRepository.findByResumeId(resumeId);
        List<Education> educations = educationRepository.findByResumeId(resumeId);
        List<Experience> experiences = experienceRepository.findByResumeId(resumeId);
        ResumeVO resumeVO = new ResumeVO(personInfo,resume,skills,educations,experiences);
        return resumeVO;
    }
    public void delete(String id) {
        if (!resumeRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        List<Skill> skills = skillRepository.findByResumeId(id);
        if (skills.size() != 0) {
            for (Skill skill:
                 skills) {
                skillRepository.delete(skill);
            }
        }
        List<Education> educations = educationRepository.findByResumeId(id);
        if (educations.size() != 0) {
            for (Education education:
                 educations) {
                educationRepository.delete(education);
            }
        }
        List<Experience> experiences = experienceRepository.findByResumeId(id);
        if (experiences.size() != 0) {
            for (Experience experience:
                 experiences) {
                experienceRepository.delete(experience);
            }
        }
        resumeRepository.deleteById(id);
    }
}
