package com.huayue.common.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/10.
 */
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class SuperEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @CreatedBy
    private String createBy;
    @CreatedDate
    private Date createTime;
    @LastModifiedBy
    private String lastUpdateBy;
    @LastModifiedDate
    private Date lastUpdateTime;
}
