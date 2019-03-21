package com.huayue.job.entity;

import com.huayue.common.entity.SuperEntity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/13.
 */
@Entity
@Data
@Table(name = "industry")
public class Industry extends SuperEntity {
    private String name;
    private String parentId;
    @Transient
    private List<JobType> jobTypes;
}
