package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.UserDao;
import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final static String KEY = "groupfour";

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
//        user.setPassword(DigestUtils.md5DigestAsHex((password + KEY).getBytes()));
        user.setPassword(password);
        int id = userDao.registerUser(user);
        user.setId(id);
        return user;
    }

    public Boolean existUser(String username) {
        return userDao.getUserByUsername(username) != null;
    }

    public User loginValid(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username,password);
//                DigestUtils.md5DigestAsHex((password + KEY).getBytes()));
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
