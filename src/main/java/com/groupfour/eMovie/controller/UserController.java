package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户API")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    @Operation(summary = "通过username获取用户")
    @Parameter(description = "用户名")
    public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
        HttpStatus code = HttpStatus.OK;
        String message = "";
        Map<String, Object> map = new HashMap<>();

        User user = userService.getUserByUsername(username);
        if (user != null) {
            message = "Find User Info.";
        } else {
            message = "User does not exist.";
        }

        map.put("message", message);
        map.put("data", user);
        return new ResponseEntity<>(map, code);
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public ResponseEntity<Object> login(@Schema(example = "{\"username\": \"lixueqi\", \"password\": \"123\"}")
                                        @RequestBody User user) {
        HttpStatus code = null;
        String message = "";
        Map<String, Object> map = new HashMap<>();

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
                if (getUser.getAccessToken() == null || getUser.getAccessToken().equals("")) {
                    long loginTime = Calendar.getInstance().getTimeInMillis();
                    getUser.setLoginTime(loginTime);
                    System.out.println("login time " + loginTime);
                    String accessToken = DigestUtils.md5DigestAsHex((getUser.getUsername() + loginTime).getBytes());
                    getUser.setAccessToken(accessToken);
                    String refreshToken = DigestUtils.md5DigestAsHex((getUser.getUsername() +
                            "refresh" + loginTime).getBytes());
                    getUser.setRefreshToken(refreshToken);
                    userService.updateUser(getUser); // update new tokens and loginTime
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            map.put("message", message);
            map.put("data", getUser);
            return new ResponseEntity<>(map, code);
        }
    }

    @PostMapping("")
    @Operation(summary = "用户注册")
    public ResponseEntity<Object> registerUser(@Schema(example = "{\"username\": \"lixueqi\", \"password\": \"123\"}")
                                               @RequestBody User user) {
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
            return new ResponseEntity<>(map, code);
        }
    }
}
