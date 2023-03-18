package com.groupfour.eMovie.authorization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.util.Calendar;

///**
// * 自定义拦截器，判断此次请求是否有权限
// * @see com.groupfour.eMovie.authorization.Authorization
// * @author lizai
// * @date 2016/4/13.
// */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final static String KEY = "groupfour";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (! (handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String token = request.getHeader("Token");
        String requestTime = request.getHeader("Request-Time");
        String clientSig = request.getHeader("Sig");
        String serverSig = "";
        if (token == null) {
            return false;
        } else {
            serverSig = DigestUtils.md5DigestAsHex((token + KEY + requestTime).getBytes());
        }
        if (clientSig != null && serverSig.trim().equals(clientSig.trim())) {
            if ((Calendar.getInstance().getTimeInMillis() - Long.parseLong(requestTime)) / 1000 < 300) {
                return true;
            }
        }
        return false;
    }
}
