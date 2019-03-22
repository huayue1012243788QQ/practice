package com.huayue.common.enums;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
public enum EducationRank {
    HIGH_SCHOOL,
    TECHNICAL_SECONDARY_SCHOOL,
    JUNIOR_COLLEGE,
    UNDERGRADUATE,
    MASTER,
    DOCTOR;
    public static boolean check(String rank) {
        for (EducationRank educationRank:
             EducationRank.values()) {
            if (rank.equals(educationRank)) {
                return true;
            }
        }
        return false;
    }
}
