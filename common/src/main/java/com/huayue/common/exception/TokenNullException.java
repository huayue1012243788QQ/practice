package com.huayue.common.exception;

import com.huayue.common.constant.ExceptionConst;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/14.
 * @description
 */
public class TokenNullException extends ApiException {
    public TokenNullException() {
        super(ExceptionConst.TOKEN_NULL_CODE, ExceptionConst.TOKEN_NULL_MSG);
    }
}
