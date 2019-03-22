package com.huayue.common.entity;

import com.huayue.common.enums.check.Check;
import lombok.Data;

import javax.persistence.MappedSuperclass;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@MappedSuperclass
@Data
public abstract class CheckEntity extends SuperEntity {
    private String checked = Check.UNCHECKED.toString();
}
