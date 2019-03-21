package com.huayue.job.entity;

import com.huayue.common.entity.SuperEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "job_type")
public class JobType extends SuperEntity {
    private String name;
    private String industryId;
}
