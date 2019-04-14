package com.huayue.job.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.job.entity.Job;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Repository
public interface JobRepository extends BaseRepository<Job> {
    List<Job> findByCompanyId(String id);
    List<Job> findByChecked(String check);
}
