package com.huayue.common.exception;

import com.huayue.common.constant.ExceptionConst;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/26.
 * @description
 */
public class EnumErrorException extends ApiException {
    public EnumErrorException() {
        super(ExceptionConst.ENUM_ERROR_CODE, ExceptionConst.ENUM_ERROR_MSG);
    }
}
