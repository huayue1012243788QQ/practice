package com.huayue.resume.entity;

import com.huayue.common.entity.SuperEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@Entity
@Data
public class Resume extends SuperEntity {
    private String personInfoId;
    private String expectJob;
    private String workingCity;
    private String selfEvaluation;
    private String worksLink;
    private String resumeType;
    private String attachment;
}
