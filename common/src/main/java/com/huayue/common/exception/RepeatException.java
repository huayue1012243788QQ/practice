package com.huayue.common.exception;

import com.huayue.common.constant.ExceptionConst;
import lombok.Data;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 * @description 数据库插入时，经检测要插入的值已存在，则抛出此错误并返回失败信息
 */
@Data
public class RepeatException extends ApiException {
    public RepeatException() {
        super(ExceptionConst.REPEAT_CODE, ExceptionConst.REPEAT_MSG);
    }
}
