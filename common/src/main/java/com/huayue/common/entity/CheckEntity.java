package com.huayue.common.entity;

import com.huayue.common.enums.Check;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
@MappedSuperclass
@Data
public abstract class CheckEntity extends SuperEntity {
    @Enumerated(EnumType.STRING)
    private Check checked = Check.UNCHECKED;
}
