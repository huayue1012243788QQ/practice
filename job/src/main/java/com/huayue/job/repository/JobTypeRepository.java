package com.huayue.job.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.job.entity.JobType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@Repository
public interface JobTypeRepository extends BaseRepository<JobType> {
    boolean existsByName(String name);
    JobType findByName(String name);
    List<JobType> findByIndustryId(String id);
}
