package com.huayue.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.huayue.common.entity.SuperEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/14.
 */
@Data
@Table(name = "sys_role")
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role extends SuperEntity implements GrantedAuthority, Serializable {
    @Column(name = "name")
    private String name;
    @Column(name = "remark")
    private String remark;
    @Column(name = "del_flag")
    private boolean delFlag;
//    @ManyToMany(targetEntity = User.class,cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
//    @JoinTable(name = "sys_user_role",
//            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
//    private List<User> users;
    @Override
    public String getAuthority() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
