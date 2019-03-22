package com.huayue.common.enums.check;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description
 */
public enum CheckResultEnum {
    PASS,
    FAIL;
    public static boolean check(String checkResult) {
        for (CheckResultEnum resultEnum:
                CheckResultEnum.values()) {
            if (checkResult.equals(resultEnum.toString())) {
                return true;
            }
        }
        return false;
    }
}
