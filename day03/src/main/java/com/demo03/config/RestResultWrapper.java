package com.demo03.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;


@RestControllerAdvice
public class RestResultWrapper implements ResponseBodyAdvice<Object> {

    /**
     * 可指定针对某些返回值的类型才进行rest风格的封装
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        Method returnTypeMethod = returnType.getMethod();
//        if (returnTypeMethod != null) {
//            return !returnTypeMethod.isAnnotationPresent(RestSkip.class);
//        }
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (MediaType.IMAGE_JPEG.getType().equalsIgnoreCase(selectedContentType.getType())) {
            return body;
        }
        if (body instanceof RestResult) {
            return body;
        }
        return new RestResult("1", body, "");
    }
}
