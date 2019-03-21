package com.huayue.userservice.repository;

import com.huayue.common.repository.BaseRepository;
import com.huayue.userservice.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/12.
 * @description
 */
@Repository
public interface RoleRepository extends BaseRepository<Role> {
    boolean existsByName(String name);
    Role findByName(String name);
}
