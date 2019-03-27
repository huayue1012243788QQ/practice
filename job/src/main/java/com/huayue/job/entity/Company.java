package com.huayue.job.entity;

import com.huayue.common.entity.CheckEntity;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@Entity
@Data
public class Company extends CheckEntity {
    private String name;
    private String description;
    private String address;
    private String scale;
    private String industryId;
    @Transient
    private List<Job> jobs;
    private String userId;
    @Transient
    private Industry industry;
    private String image;
}
