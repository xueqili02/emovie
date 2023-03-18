package com.groupfour.eMovie.authorization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

///**
// * 自定义拦截器，判断此次请求是否有权限
// * @see com.groupfour.eMovie.authorization.Authorization
// * @author lizai
// * @date 2016/4/13.
// */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (! (handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String a = request.getHeader("Host");
        System.out.println(a);
        return true;
    }
}
