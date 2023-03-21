package com.groupfour.eMovie.authorization;

import com.groupfour.eMovie.dao.UserDao;
import com.groupfour.eMovie.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
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

    private final static String ACCESS_KEY = "accessgroupfour"; // symmetric key 对称加密
    private final static String REFRESH_KEY = "refreshgroupfour"; // symmetric key 对称加密

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
        String loginTime = request.getHeader("Login-Time"); // last login time
        String clientAccessCypher = request.getHeader("Access-Cypher"); // client-generated access cypher
        String clientRefreshCypher = request.getHeader("Refresh-Cypher"); // client-generated refresh cypher
        String serverAccessCypher = ""; // server-generated access cypher
        String serverRefreshCypher = ""; // server-generated refresh cypher

        // if client sends refresh token, then generate new access token and send back
        if (refreshToken != null && !refreshToken.equals("")) {
            serverRefreshCypher = DigestUtils.md5DigestAsHex((refreshToken + REFRESH_KEY + loginTime).getBytes());
            // client cypher equals server cypher
            if (clientRefreshCypher != null && serverRefreshCypher.trim().equals(clientRefreshCypher.trim())) {
                // refresh token does not expire
                if ((Calendar.getInstance().getTimeInMillis() - Long.parseLong(loginTime)) / 1000 <= 604800) { // 7 days

                    User user = userDao.getUserByRefreshToken(refreshToken); // find current user

                    // generate new access token and update database
                    String nowTime = Long.toString(Calendar.getInstance().getTimeInMillis());
                    String newAccessToken = DigestUtils.md5DigestAsHex((user.getUsername() + nowTime).getBytes());
                    user.setAccessToken(newAccessToken);
                    user.setLoginTime(Long.parseLong(nowTime));
                    userDao.updateUser(user);

                    // set response header
                    response.setHeader("Access-Token", newAccessToken);
                    response.setHeader("Login-Time", nowTime);
                    System.out.println("interceptor now time " + nowTime);
                }

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
        }

        // if no refresh token, then authenticate access token
        if (accessToken == null || accessToken.equals("")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        } else {
            serverAccessCypher = DigestUtils.md5DigestAsHex((accessToken + ACCESS_KEY + loginTime).getBytes());
        }
        if (clientAccessCypher != null && serverAccessCypher.trim().equals(clientAccessCypher.trim())) {
            if ((Calendar.getInstance().getTimeInMillis() - Long.parseLong(loginTime)) / 1000 <= 300) { // 15 min
                return true;
            }
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;
    }
}
