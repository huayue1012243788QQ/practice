package com.huayue.applyservice.entity;

import com.huayue.common.entity.SuperEntity;
import lombok.Data;

import javax.persistence.Entity;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/20.
 * @description 职位申请表，对应公司ID和简历ID
 */
@Data
@Entity
public class Apply extends SuperEntity {
    private String jobId;
    private String resumeId;
    private String status;
}
