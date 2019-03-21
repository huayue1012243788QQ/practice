package com.huayue.resume.entity;

import com.huayue.common.entity.SuperEntity;
import com.huayue.common.enums.ExperienceType;
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
@Data
@Entity
public class Experience extends SuperEntity {
    @Enumerated(EnumType.STRING)
    private ExperienceType experienceType;
    private String title;
    private String position;
    private Date start;
    private Date end;
    private String description;
    private String resumeId;
}
