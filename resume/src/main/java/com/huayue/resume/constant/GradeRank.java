package com.huayue.resume.constant;

import lombok.Data;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/25.
 * @description
 */
@Data
public class GradeRank {
    public static String EXCELLENT = "前10%";
    public static String GENERAL = "前30%";
    public static String MIDDLE = "前50%";
    public static boolean checkRank(String rank) {
        if (rank.equals(EXCELLENT) || rank.equals(GENERAL) || rank.equals(MIDDLE)) {
            return true;
        }
        return false;
    }
}
