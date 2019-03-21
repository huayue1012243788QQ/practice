package com.huayue.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaSpecificationExecutor<T>, JpaRepository<T, String> {
    
}
