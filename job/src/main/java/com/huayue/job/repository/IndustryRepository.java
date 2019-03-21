package com.huayue.job.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.job.entity.Industry;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@Repository
public interface IndustryRepository extends BaseRepository<Industry> {
    Industry findByName(String name);
    boolean existsByName(String name);
    boolean existsByParentId(String parentId);
    List<Industry> findByParentId(String id);
}
