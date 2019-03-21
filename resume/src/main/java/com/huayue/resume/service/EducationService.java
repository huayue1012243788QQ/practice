package com.huayue.resume.service;

import com.huayue.common.exception.EnumErrorException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.resume.constant.GradeRank;
import com.huayue.resume.entity.Education;
import com.huayue.resume.repository.EducationRepository;
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
    @Override
    public BaseRepository<Education> getRepository() {
        return educationRepository;
    }
    public Education save(Education education) {
        if (!GradeRank.checkRank(education.getGradeRank())) {
            throw new EnumErrorException();
        }
        return null;
    }
}
