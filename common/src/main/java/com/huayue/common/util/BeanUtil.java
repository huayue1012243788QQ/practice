package com.huayue.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/21.
 * @description
 */
public class BeanUtil {
    /**
     * 封装bean的copy方法，解决JPA更新数据时对null值属性的修改问题
     * （若更新数据传入对象的某一值为null，通常意义上即为不修改该值，但JPA会将数据库中该值设为null）
     * @param src
     * @param target
     */
    public static void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
    /**
     * 获取新建对象中属性值为空的属性集
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
