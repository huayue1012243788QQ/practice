package com.huayue.common.exception;

import com.huayue.common.constant.ExceptionConst;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/3/20.
 * @description
 */
public class UncheckException extends ApiException {
    public UncheckException(String id) {
        super(ExceptionConst.UNCHECK_CODE, ExceptionConst.UNCHECK_MSG+id);
    }
}
