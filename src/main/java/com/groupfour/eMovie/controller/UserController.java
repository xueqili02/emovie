package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        HttpStatus code = HttpStatus.OK;
        String message = "";
        Map<String, Object> map = new HashMap<String, Object>();

        User user = userService.getUserByUsername(username);
        if (user != null) {
            message = "Find User Info.";
        } else {
            message = "User does not exist.";
        }

        map.put("message", message);
        map.put("data", user);
        return new ResponseEntity<Object>(map, code);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        HttpStatus code = null;
        String message = "";
        Map<String, Object> map = new HashMap<String, Object>();

        User getUser = null;
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

            getUser = userService.loginValid(user.getUsername(), user.getPassword());
            if (getUser == null) {
                message = "Invalid username or password.";
                code = HttpStatus.BAD_REQUEST;
            } else {
                message = "Login Success.";
                code = HttpStatus.OK;
                if (getUser.getToken() == null || getUser.getToken().equals("")) {
                    String token = DigestUtils.md5DigestAsHex(
                            (getUser.getUsername() + Calendar.getInstance().getTimeInMillis()).getBytes());
                    getUser.setToken(token);
                    userService.updateUser(getUser);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            map.put("message", message);
            map.put("data", getUser);
            return new ResponseEntity<Object>(map, code);
        }
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
