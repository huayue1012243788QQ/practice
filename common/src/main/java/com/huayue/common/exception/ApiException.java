package com.huayue.common.exception;

import lombok.Data;

/**
 * @author huayue.
 * @email huayuechn@gmail.com
 * @date 2019/2/18.
 * @description
 */
@Data
public class ApiException extends RuntimeException {
    private int retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息

    public ApiException(int retCd, String msgDes) {
        this.retCd = retCd;
        this.msgDes = msgDes;
    }
}
