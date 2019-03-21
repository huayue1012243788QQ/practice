package com.huayue.resume.service;

import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.resume.entity.Experience;
import com.huayue.resume.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 */
@Service
public class ExperienceService extends BaseService<Experience> {
    @Autowired
    private ExperienceRepository experienceRepository;
    @Override
    public BaseRepository<Experience> getRepository() {
        return experienceRepository;
    }
}
