package com.huayue.resume.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.resume.entity.PersonInfo;
import org.springframework.stereotype.Repository;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Repository
public interface PersonInfoRepository extends BaseRepository<PersonInfo> {
    boolean existsByUserId(String id);
}
