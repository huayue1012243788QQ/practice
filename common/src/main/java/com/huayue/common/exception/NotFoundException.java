package com.huayue.common.exception;

import com.huayue.common.constant.ExceptionConst;
import lombok.Data;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 * @description
 */
@Data
public class NotFoundException extends ApiException {
    public NotFoundException(String ID) {
        super(ExceptionConst.NOT_FOUND_CODE, ExceptionConst.NOT_FOUND_MSG+ID);
    }
}
