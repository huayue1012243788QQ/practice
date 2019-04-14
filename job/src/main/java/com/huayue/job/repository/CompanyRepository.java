package com.huayue.job.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.job.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Repository
public interface CompanyRepository extends BaseRepository<Company> {
    boolean existsByName(String name);
    Company findByName(String name);
    boolean existsByUserId(String userId);
    List<Company> findByUserId(String userId);
    List<Company> findByChecked(String checked);
}
