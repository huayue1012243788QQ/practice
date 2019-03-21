package com.huayue.resume.entity;

import com.huayue.common.entity.SuperEntity;
import com.huayue.common.enums.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Entity
@Data
public class PersonInfo extends SuperEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date birthday;
    private String country;
    private String city;
    private String userId;
}
