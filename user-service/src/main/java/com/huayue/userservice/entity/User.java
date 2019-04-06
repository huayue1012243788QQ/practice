package com.huayue.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.huayue.common.entity.SuperEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/10.
 */
@Entity
@Table(name="sys_user")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User extends SuperEntity implements UserDetails, Serializable {
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="salt")
    private String salt;
    @Column(name="status")
    private boolean status;
    @Column(name="del_flag")
    private boolean delFlag;
    @ManyToMany(targetEntity = Role.class,cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private List<Role> authorities;
    public User(String username,String password) {
        this.username = username;
        this.password = password;
    }
    public User() {

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
