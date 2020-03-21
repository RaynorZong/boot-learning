package com.demo.interceptor;

import com.demo.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Resource
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        log.info("请求路径：{}", request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            response.getWriter().print("用户未登录，请登录后操作！");
            return false;
        }
        Object loginStatus = redisService.getToken(token);
        if( Objects.isNull(loginStatus)){
            response.getWriter().print("token错误，请查看！");
            return false;
        }
        return true;
    }
}
