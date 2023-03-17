package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User newUser = userService.registerUser(user.getUsername(), user.getPassword());
        return "register";
    }
}
