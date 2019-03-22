package com.huayue.common.exception;

import com.huayue.common.constant.ExceptionConst;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/22.
 * @description
 */
public class CheckErrorException extends ApiException {
    public CheckErrorException() {
        super(ExceptionConst.CHECK_ERROR_CODE, ExceptionConst.CHECK_ERROR_MSG);
    }
}
