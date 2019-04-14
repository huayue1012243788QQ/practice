package com.huayue.applyservice.repository;

import com.huayue.applyservice.entity.Apply;
import com.huayue.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/20.
 * @description
 */
@Repository
public interface ApplyRepository extends BaseRepository<Apply> {
    boolean existsByJobIdAndResumeId(String jobId, String resumeId);
    Apply findByJobId(String id);
}
