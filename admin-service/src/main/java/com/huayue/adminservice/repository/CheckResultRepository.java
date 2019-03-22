package com.huayue.adminservice.repository;

import com.huayue.adminservice.entity.CheckResult;
import com.huayue.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description
 */
@Repository
public interface CheckResultRepository extends BaseRepository<CheckResult> {
    boolean existsByCheckedId(String checkedId);
}
