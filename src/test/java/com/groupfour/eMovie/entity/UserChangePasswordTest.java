package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserChangePasswordTest {
    @Test
    void getUserName() {
        assertEquals("chrini", new UserChangePassword("chrini", "abc123","qwe123").getUsername());
    }

    @Test
    void setUserName(){
        UserChangePassword userChangePassword = new UserChangePassword("chrini", "abc123","qwe123");
        userChangePassword.setUsername("wyp");
        assertEquals("wyp", userChangePassword.getUsername());
    }

    void getOldPassword() {
        assertEquals("abc123", new UserChangePassword("chrini", "abc123","qwe123").getOldPassword());
    }

    @Test
    void setOldPassword(){
        UserChangePassword userChangePassword = new UserChangePassword("chrini", "abc123","qwe123");
        userChangePassword.setOldPassword("abcd123");
        assertEquals("abcd123", userChangePassword.getOldPassword());
    }

    void getNewPassword() {
        assertEquals("qwe123", new UserChangePassword("chrini", "abc123","qwe123").getNewPassword());
    }

    @Test
    void setNewPassword(){
        UserChangePassword userChangePassword = new UserChangePassword("chrini", "abc123","qwe123");
        userChangePassword.setNewPassword("qwer123");
        assertEquals("qwer123", userChangePassword.getNewPassword());
    }
}
