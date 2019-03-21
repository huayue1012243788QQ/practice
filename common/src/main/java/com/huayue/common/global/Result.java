package com.huayue.common.global;

import lombok.Data;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/1/14.
 */
@Data
public class Result {
    public final static int SUCCESS = 200;
    public final static int FAILURE = 505;
    private int code = SUCCESS;
    private String message = "成功";
    private Object data;

    public Result() {
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Result(Object data) {
        this.data = data;
    }

    public static Result success() {
        return new Result();
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result failure() {
        return new Result(FAILURE, "失败", null);
    }

    public static Result failure(String message) {
        return new Result(FAILURE, message);
    }

    public static Result failure(int code, String message) {
        return new Result(code, message);
    }

    public static Result of(int code, String message) {
        return new Result(code, message);
    }

    public static Result of(int code, String message, Object data) {
        return new Result(code, message, data);
    }
}
