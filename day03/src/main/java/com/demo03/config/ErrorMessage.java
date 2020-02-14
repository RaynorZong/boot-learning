package com.demo03.config;

public enum ErrorMessage {
    SYSTEM_EXCEPTION ("系统异常"),
    LOGIC_EXCEPTION("业务异常");

    public String msg;


    ErrorMessage(String msg){
        this.msg = msg;
    }
    public String msg() {
        return this.msg;
    }
}
