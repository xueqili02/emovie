package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        User newUser = null;
        HttpStatus code = null;
        String message = "";
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            if (user.getUsername() == null || user.getUsername().equals("")) {
                code = HttpStatus.BAD_REQUEST;
                message = "Username is null.";
                throw new Exception("Username is null.");
            } else if (user.getPassword() == null || user.getPassword().equals("")) {
                code = HttpStatus.BAD_REQUEST;
                message = "Password is null.";
                throw new Exception("Password is null.");
            }

            if (userService.existUser(user.getUsername())) {
                code = HttpStatus.BAD_REQUEST;
                message = "User already exists.";
                throw new Exception("User already exists.");
            } else {
                newUser = userService.registerUser(user.getUsername(), user.getPassword());
                code = HttpStatus.CREATED;
                message = "Register Success";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            map.put("message", message);
            map.put("data", newUser);
            return new ResponseEntity<Object>(map, code);
        }
    }
}
