package com.huayue.job.entity;

import com.huayue.common.entity.CheckEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@Entity
@Table(name = "job")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Job extends CheckEntity {
    private String title;
    private int minSalary;
    private int maxSalary;
    private int workDay;   //每周实习天数
    private int workTime;  //实习时长，单位为月
    private String educationRank; //学历
    private String description;
    private String address;
    private String city;
    private String jobTypeId;
    private String companyId;
    @Transient
    private Company company;
    @Transient
    private JobType jobType;
}
