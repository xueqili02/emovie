package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.User;

public interface UserService {

    User getUserByUsername(String username);

    User registerUser(String username, String password);

    Boolean existUser(String username);

    User loginValid(String username, String password);

    void updateUser(User user);

    void changePassword(String newPassword, String username);
}
