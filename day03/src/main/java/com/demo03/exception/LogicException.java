package com.demo03.exception;

import lombok.Data;

@Data
public class LogicException extends RuntimeException{
    private String errorMsg;
    private String code;
    private LogicException(String errorMsg) {
        super(errorMsg);
        this.code = errorMsg.substring(0, 5);
        this.errorMsg = errorMsg.substring(6);
    }
    public static LogicException le(String errorMsg) {
        return new LogicException(errorMsg);
    }
}
