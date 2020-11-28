package com.raynor.utils;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RestResult {
    private String code;
    private Object data;
    private Object errorMessage;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime currentTime;

    public RestResult() {
    }

    @Override
    public String toString() {
        return "RestResult{" +
                ", code='" + code + '\'' +
                ", data=" + data +
                ", errorMessage=" + errorMessage +
                ", currentTime=" + currentTime +
                '}';
    }

    public RestResult(String code, Object data, Object errorMessage) {
        this.code = code;
        this.data = data;
        this.errorMessage = errorMessage;
        this.currentTime = LocalDateTime.now();
    }

}
