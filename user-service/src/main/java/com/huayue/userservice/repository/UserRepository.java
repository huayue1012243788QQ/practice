package com.huayue.userservice.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.userservice.entity.User;
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
    boolean existsByUsername(String username);
}
