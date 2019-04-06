package com.huayue.resume.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.resume.entity.Resume;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/15.
 */
@Repository
public interface ResumeRepository extends BaseRepository<Resume> {
    List<Resume> findByPersonInfoId(String personInfoId);
}
