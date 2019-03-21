package com.huayue.resume.entity;

import com.huayue.common.entity.SuperEntity;
import com.huayue.common.enums.EducationRank;
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
public class Education extends SuperEntity {
    private String profession;
    private String gradeRank;
    private String courses;
    private String school;
    private Date start;
    private Date end;
    @Enumerated(EnumType.STRING)
    private EducationRank educationRank;
    private String awards;
    private String resumeId;
}
