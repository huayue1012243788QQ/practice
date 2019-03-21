package com.huayue.common.exception;

import com.huayue.common.constant.ExceptionConst;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/20.
 * @description
 */
public class CheckRepeatException extends ApiException {
    public CheckRepeatException() {
        super(ExceptionConst.CHECK_REPEAT_CODE, ExceptionConst.CHECK_REPEAT_MSG);
    }
}
