package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.User;

public interface UserService {

    public User getUserByUsername(String username);

    public User registerUser(String username, String password);
}
