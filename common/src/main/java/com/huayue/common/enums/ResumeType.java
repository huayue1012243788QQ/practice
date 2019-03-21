package com.huayue.common.enums;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
public enum ResumeType {
    TEMPLATE,
    FILE;
    public static boolean contains(String value) {
        for (ResumeType resumeType
                :ResumeType.values()) {
            if (resumeType.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
