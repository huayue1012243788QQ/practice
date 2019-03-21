package com.huayue.common.enums;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/14.
 */
public enum Scale {
    A("15"),B("15~50");
    private final String value;
    private Scale(String value) {
        this.value = value;
    }
    private String getValue() {
        return value;
    }

}
