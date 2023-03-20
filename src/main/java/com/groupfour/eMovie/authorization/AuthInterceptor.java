package com.groupfour.eMovie.authorization;

import com.groupfour.eMovie.dao.UserDao;
import com.groupfour.eMovie.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (! (handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        String accessToken = request.getHeader("Access-Token");
        String refreshToken = request.getHeader("Refresh-Token");
        String requestTime = request.getHeader("Request-Time");
        String clientSig = request.getHeader("Sig");
        String serverSig = "";

        if (accessToken == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        } else {
            serverSig = DigestUtils.md5DigestAsHex((accessToken + KEY + requestTime).getBytes());
        }
        if (clientSig != null && serverSig.trim().equals(clientSig.trim())) {
            if ((Calendar.getInstance().getTimeInMillis() - Long.parseLong(requestTime)) / 1000 < 1800) {
                return true;
            }
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
}
