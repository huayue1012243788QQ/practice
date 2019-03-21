package com.huayue.userservice.service;

import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.PasswordErrorException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.exception.TokenNullException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.userservice.client.AuthServiceClient;
import com.huayue.userservice.dto.JWT;
import com.huayue.userservice.dto.LoginDTO;
import com.huayue.userservice.dto.UserLoginDTO;
import com.huayue.userservice.entity.Role;
import com.huayue.userservice.entity.User;
import com.huayue.userservice.repository.RoleRepository;
import com.huayue.userservice.repository.UserRepository;
import com.huayue.userservice.util.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/6.
 * @description
 */
@Service
public class UserService extends BaseService<User> implements UserDetailsService {
    private static final String CLIENT_SECRET = "Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==";
    @Autowired
    private AuthServiceClient client;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public BaseRepository<User> getRepository() {
        return userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RepeatException();
        }
        user.setPassword(BPwdEncoderUtil.BCryptPassword(user.getPassword()));
        return userRepository.save(user);
    }
    public UserLoginDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user == null) {
            throw new NotFoundException(loginDTO.getUsername());
        }
        if (!BPwdEncoderUtil.matches(loginDTO.getPassword(),user.getPassword())) {
            throw new PasswordErrorException();
        }
        JWT jwt = client.getToken(CLIENT_SECRET,"password",loginDTO.getUsername(),loginDTO.getPassword());
        if (jwt == null) {
            throw new TokenNullException();
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;
    }
    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            throw new NotFoundException(user.getId());
        }
        User user1 = userRepository.findById(user.getId()).get();
        if (userRepository.existsByUsername(user.getUsername())) {
            if (!userRepository.findByUsername(user.getUsername()).getId().equals(user1.getId())) {
                throw new RepeatException();
            }
        }
        BeanUtil.copyNonNullProperties(user,user1);
        return userRepository.saveAndFlush(user1);
    }
    public void delete(String id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        userRepository.deleteById(id);
    }
    public User changeAuthority(String id, List<Role> roles) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        for (Role role:
             roles) {
            if (!roleRepository.existsById(role.getId())) {
                throw new NotFoundException(role.getId());
            }
        }
        User user = userRepository.findById(id).get();
        user.setAuthorities(roles);
        return userRepository.saveAndFlush(user);
    }
}
