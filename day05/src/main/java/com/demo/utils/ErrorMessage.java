package com.demo.utils;

public enum ErrorMessage {
    SYSTEM_EXCEPTION ("系统异常"),
    LOGIC_EXCEPTION("业务异常");

    private String msg;


    ErrorMessage(String msg){
        this.msg = msg;
    }

    public String msg() {
        return this.msg;
    }
}
