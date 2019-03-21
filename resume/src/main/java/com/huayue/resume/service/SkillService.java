package com.huayue.resume.service;

import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.resume.entity.Skill;
import com.huayue.resume.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 */
@Service
public class SkillService extends BaseService<Skill> {
    @Autowired
    private SkillRepository skillRepository;
    @Override
    public BaseRepository<Skill> getRepository() {
        return skillRepository;
    }
}
