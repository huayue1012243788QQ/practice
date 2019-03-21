package com.huayue.authservice.service;

import com.huayue.authservice.entity.User;
import com.huayue.authservice.repository.UserRepository;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/6.
 * @description
 */
@Service
public class UserService extends BaseService<User> implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public BaseRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
}
