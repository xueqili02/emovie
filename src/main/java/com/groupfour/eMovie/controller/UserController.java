package com.groupfour.eMovie.controller;

import com.groupfour.eMovie.entity.*;
import com.groupfour.eMovie.service.UserService;
import com.groupfour.eMovie.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

import static com.groupfour.eMovie.utils.ProjectConstants.FAILURE;

@RestController
@RequestMapping("/users")
@Tag(name = "用户API")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    @Operation(summary = "通过username获取用户")
    @Parameter(description = "用户名")
    public Result getUserByUsername(@PathVariable String username) {
        HttpStatus code = HttpStatus.OK;
        String message = "";

        User user = userService.getUserByUsername(username);
        if (user != null) {
            message = "Find User Info.";
        } else {
            message = "User does not exist.";
        }

        return new Result(code.value(), message, user);
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result login(@Schema(example = "{\"username\": \"lixueqi\", \"password\": \"123\"}")
                                        @RequestBody User user) {
        HttpStatus code = null;
        String message = "";

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
                long loginTime = Calendar.getInstance().getTimeInMillis();
                getUser.setLoginTime(loginTime);
                String accessToken = DigestUtils.md5DigestAsHex((getUser.getUsername() + loginTime).getBytes());
                getUser.setAccessToken(accessToken);
                String refreshToken = DigestUtils.md5DigestAsHex((getUser.getUsername() +
                        "refresh" + loginTime).getBytes());
                getUser.setRefreshToken(refreshToken);
                userService.updateUser(getUser); // update new tokens and loginTime
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return new Result(code.value(), message, getUser);
        }
    }

    @PostMapping("/login/email")
    public Result loginByEmail(@RequestBody LoginCode loginCode) {
        HttpStatus code = null;
        String message = "";
        User getUser = userService.loginByCode(loginCode);

        if (getUser == null) {
            code = HttpStatus.BAD_REQUEST;
            message = "failure";
            return new Result(code.value(), message, null);
        }

        code = HttpStatus.OK;
        message = "success";
        return new Result(code.value(), message, getUser);
    }

    @PostMapping("/code")
    public Result sendCode(@RequestBody LoginEmail loginEmail) {
        HttpStatus code = null;
        String message = "";

        if (userService.sendCodeByEmail(loginEmail) == FAILURE) {
            code = HttpStatus.BAD_REQUEST;
            message = "Fail to send code";
            return new Result(code.value(), message, null);
        }

        code = HttpStatus.OK;
        message = "success";
        return new Result(code.value(), message, null);
    }

    @PostMapping("")
    @Operation(summary = "用户注册")
    public Result registerUser(@Schema(example = "{\"username\": \"lixueqi\", \"password\": \"123\"}")
                                               @RequestBody User user) {
        User newUser = null;
        HttpStatus code = null;
        String message = "";

        try {
            if (user.getUsername() == null || user.getUsername().equals("")) {
                code = HttpStatus.BAD_REQUEST;
                message = "Username is null.";
                throw new Exception(message);
            } else if (user.getPassword() == null || user.getPassword().equals("")) {
                code = HttpStatus.BAD_REQUEST;
                message = "Password is null.";
                throw new Exception(message);
            } else if (user.getEmail() == null || user.getEmail().equals("")) {
                code = HttpStatus.BAD_REQUEST;
                message = "Email is null.";
                throw new Exception(message);
            }

            if (userService.existUser(user)) {
                code = HttpStatus.BAD_REQUEST;
                message = "User already exists.";
                throw new Exception("User already exists.");
            } else {
                newUser = userService.registerUser(user);
                code = HttpStatus.CREATED;
                message = "Register Success";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return new Result(code.value(), message, newUser);
        }
    }

    @PatchMapping("/password/{username}")
    @Operation(summary = "修改密码")
    @Parameter(description = "username")
    public Result changePassword(@PathVariable String username,
                                 @Schema(example = "{\"oldPassword\": \"md5\", \"newPassword\": \"md5\"}")
                                 @RequestBody UserChangePassword patch) {
        HttpStatus code = null;
        String message = "";

        User user = userService.getUserByUsername(username);
        if (user == null) {
            code = HttpStatus.BAD_REQUEST;
            message = "User does not exist.";
            return new Result(code.value(), message, "");
        }

        if (patch.getOldPassword() == null || patch.getOldPassword().equals("") ||
            patch.getNewPassword() == null || patch.getNewPassword().equals("")) {
            code = HttpStatus.BAD_REQUEST;
            message = "Password is null.";
            return new Result(code.value(), message, "");
        }

        // old password matches
        if (user.getPassword() != null && patch.getOldPassword().equals(user.getPassword())) {
            userService.changePassword(patch.getNewPassword(), username);
            code = HttpStatus.OK;
            message = "success";
        } else if (user.getPassword() == null) {
            code = HttpStatus.BAD_REQUEST;
            message = "Cannot find user password.";
        } else {
            code = HttpStatus.BAD_REQUEST;
            message = "Old password does not match.";
        }

        return new Result(code.value(), message, "");
    }

    @PostMapping("/rating")
    @Operation(summary = "用户评分")
    public Result rateMovie(@Schema(example = "{\"uid\": \"1\", \"movieid\": \"862\", \"rating\": \"5\"}")
                                @RequestBody Rating rating) {
        HttpStatus code = null;
        String message = "";

        userService.rateMovie(rating);
        code = HttpStatus.OK;
        message = "success";

        return new Result(code.value(), message, "");
    }

    @GetMapping("/ratingrecord/{uid}")
    @Operation(summary = "获取评分记录")
    @Parameter(description = "user id")
    public Result getRatingRecord(@PathVariable int uid) {
        HttpStatus code = null;
        String message = "";

        List<RatingRecord> recordList = userService.getRatingRecord(uid);
        code = HttpStatus.OK;
        message = "success";

        return new Result(code.value(), message, recordList);
    }
}
