package com.huayue.resume.entity;

import com.huayue.common.entity.SuperEntity;
import com.huayue.common.enums.SkillType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Data
@Entity
public class Skill extends SuperEntity {
    @Enumerated(EnumType.STRING)
    private SkillType skillType;
    private String description;
    private String resumeId;
}
