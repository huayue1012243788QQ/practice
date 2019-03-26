package com.huayue.job.entity;

import com.huayue.common.entity.CheckEntity;
import com.huayue.common.enums.EducationRank;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private EducationRank educationRank; //学历
    private String description;
    private String address;
    private String city;
    private String jobTypeId;
    private String companyId;
}
