package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.*;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);

    User registerUser(User user);

    Boolean existUser(User user);

    User loginValid(String username, String password);

    void updateUser(User user);

    void changePassword(String newPassword, String username);

    void rateMovie(Rating rating);

    List<RatingRecord> getRatingRecord(int uid);

    int sendCodeByEmail(LoginEmail loginEmail);

    User loginByCode(LoginCode loginCode);
}
