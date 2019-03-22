package com.huayue.common.constant;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 * @description
 */
public class ExceptionConst {
    /**
     * 对应的实体类数据查询为空
     */
    public static int NOT_FOUND_CODE = 300;
    public static String NOT_FOUND_MSG = "不存在的ID值：";
    /**
     * 插入值已存在
     */
    public static int REPEAT_CODE = 301;
    public static String REPEAT_MSG = "插入值已存在";
    /**
     * 插入的枚举值不存在
     */
    public static int ENUM_ERROR_CODE = 302;
    public static String ENUM_ERROR_MSG = "插入的枚举值不存在";
    /**
     * 令牌为空
     */
    public static int TOKEN_NULL_CODE = 303;
    public static String TOKEN_NULL_MSG = "令牌为空";
    /**
     * 密码错误
     */
    public static int PASSWORD_ERROR_CODE = 304;
    public static String PASSWORD_ERROR_MSG = "用户密码错误";
    /**
     * 对象重复审核
     */
    public static int CHECK_REPEAT_CODE = 305;
    public static String CHECK_REPEAT_MSG = "对象重复审核";
    /**
     * 对象未审核
     */
    public static int UNCHECK_CODE = 306;
    public static String UNCHECK_MSG = "未审核的对象ID值：";
    /**
     * 审核失败
     */
    public static int CHECK_ERROR_CODE = 307;
    public static String CHECK_ERROR_MSG = "审核失败";

}
