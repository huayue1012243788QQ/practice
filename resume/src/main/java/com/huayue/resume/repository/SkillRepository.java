package com.huayue.resume.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.resume.entity.Skill;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 */
@Repository
public interface SkillRepository extends BaseRepository<Skill> {
    List<Skill> findByResumeId(String resumeId);
}
