package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return user;
    }

    @PostMapping("")
    public String registerUser(@RequestBody User user) {
        try {
            Assert.notNull(user.getUsername(), "username is null");
            Assert.notNull(user.getPassword(), "password is null");
            if (userService.existUser(user.getUsername())) {

            } else {
                User newUser = userService.registerUser(user.getUsername(), user.getPassword());
            }
        } catch (Exception e) {

        } finally {

        }

        return "register";
    }
}
