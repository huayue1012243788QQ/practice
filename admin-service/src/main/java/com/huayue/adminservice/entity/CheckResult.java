package com.huayue.adminservice.entity;

import com.huayue.common.entity.SuperEntity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CheckResult extends SuperEntity {
    private String checkType;     //审核内容的类型
    private String remark;        //备注
    private String checkResult;   //审核结果，枚举值
    private String checkedId;     //被审核对象的ID
}
