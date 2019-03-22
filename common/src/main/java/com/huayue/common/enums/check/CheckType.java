package com.huayue.common.enums.check;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description 审核内容的种类，包括评论审核，公司信息审核，职位信息审核
 */
public enum CheckType {
    COMPANY_INFO_CHECK,
    JOB_INFO_CHECK,
    COMMENT_INFO_CHECK;
    public static boolean check(String checkType) {
        for (CheckType type:
             CheckType.values()) {
            if (checkType.equals(type.toString())) {
                return true;
            }
        }
        return false;
    }
}
