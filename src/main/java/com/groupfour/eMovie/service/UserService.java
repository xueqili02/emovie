package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.Rating;
import com.groupfour.eMovie.entity.RatingRecord;
import com.groupfour.eMovie.entity.User;

import java.util.List;

public interface UserService {

    User getUserByUsername(String username);

    User registerUser(String username, String password);

    Boolean existUser(String username);

    User loginValid(String username, String password);

    void updateUser(User user);

    void changePassword(String newPassword, String username);

    void rateMovie(Rating rating);

    List<RatingRecord> getRatingRecord(int uid);
}
