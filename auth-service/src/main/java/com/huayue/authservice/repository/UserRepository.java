package com.huayue.authservice.repository;

import com.huayue.authservice.entity.User;
import com.huayue.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/6.
 * @description
 */
@Repository
public interface UserRepository extends BaseRepository<User> {
    User findByUsername(String username);
}
