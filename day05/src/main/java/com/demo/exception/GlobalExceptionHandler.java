package com.demo.exception;

import com.demo.utils.ErrorMessage;
import com.demo.utils.RestResult;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object logicExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        RestResult result = new RestResult("-1", e.getMessage(), ErrorMessage.SYSTEM_EXCEPTION.msg());
        //如果是业务逻辑异常，返回具体的错误码与提示信息
        if (e instanceof LogicException) {
            LogicException logicException = (LogicException) e;
            result.setCode(logicException.getCode());
            result.setErrorMessage(logicException.getErrorMsg());
        } else {
            log.info("系统异常:" + e.getMessage());
        }
        return result;
    }
}
