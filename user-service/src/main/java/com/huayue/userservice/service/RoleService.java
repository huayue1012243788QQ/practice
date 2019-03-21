package com.huayue.userservice.service;

import com.huayue.common.exception.NotFoundException;
import com.huayue.common.exception.RepeatException;
import com.huayue.common.repository.BaseRepository;
import com.huayue.common.service.BaseService;
import com.huayue.common.util.BeanUtil;
import com.huayue.userservice.entity.Role;
import com.huayue.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/12.
 * @description
 */
@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public BaseRepository<Role> getRepository() {
        return roleRepository;
    }
    public Role save(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new RepeatException();
        }
        return roleRepository.save(role);
    }
    public Role update(Role role) {
        if (!roleRepository.existsById(role.getId())) {
            throw new NotFoundException(role.getId());
        }
        Role role1 = roleRepository.findById(role.getId()).get();
        if (!roleRepository.findByName(role.getName()).getId().equals(role.getId())) {
            throw new RepeatException();
        }
        BeanUtil.copyNonNullProperties(role,role1);
        return roleRepository.saveAndFlush(role1);
    }
    public void delete(String id) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        roleRepository.deleteById(id);
    }
    public List<Role> getUserRole() {
        List<Role> roles = new ArrayList<>();
        Role roleUser = roleRepository.findByName("ROLE_USER");
        roleUser.setUsers(null);
        Role roleStu = roleRepository.findByName("ROLE_STU");
        roleStu.setUsers(null);
        Role roleCom = roleRepository.findByName("ROLE_COMPANY");
        roleCom.setUsers(null);
        roles.add(roleUser);
        roles.add(roleStu);
        roles.add(roleCom);
        return roles;
    }
}
