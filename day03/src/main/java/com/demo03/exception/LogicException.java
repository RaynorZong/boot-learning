package com.demo03.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class LogicException extends RuntimeException{
    private String errorMsg;
    private String code;
    private LogicException(String errorMsg) {
        super(errorMsg);
        this.code = errorMsg.substring(0, 1);
        this.errorMsg = errorMsg.substring(2);
    }
    public static LogicException getLogicException(String errorMsg) {
        return new LogicException(errorMsg);
    }
}
