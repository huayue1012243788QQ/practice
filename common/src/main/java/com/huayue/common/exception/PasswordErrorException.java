package com.huayue.common.exception;

import com.huayue.common.constant.ExceptionConst;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/14.
 * @description
 */
public class PasswordErrorException extends ApiException {
    public PasswordErrorException() {
        super(ExceptionConst.PASSWORD_ERROR_CODE, ExceptionConst.PASSWORD_ERROR_MSG);
    }
}
