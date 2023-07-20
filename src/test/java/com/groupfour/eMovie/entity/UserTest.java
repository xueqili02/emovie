package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    // entity测试样例，请仿照着写
    @Test
    void getId() {
        assertEquals(1, new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L).getId());
    }

    @Test
    void setId() {
        User user = new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L);
        user.setId(2);
        assertEquals(2, user.getId());
    }


    @Test
    void getusername() {
        assertEquals("lixueqi", new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L).getUsername());
    }

    @Test
    void setusername() {
        User user = new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L);
        user.setUsername("wangyipeng");
        assertEquals("wangyipeng", user.getUsername());
    }

    @Test
    void getpassword() {
        assertEquals("sdfwef", new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L).getPassword());
    }

    @Test
    void setpassword() {
        User user = new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L);
        user.setPassword("qwerty");
        assertEquals("qwerty", user.getPassword());
    }

    @Test
    void getaccessToken() {
        assertEquals("1sfwe", new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L).getAccessToken());
    }

    @Test
    void setaccessToken() {
        User user = new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L);
        user.setAccessToken("2qwer");
        assertEquals("2qwer", user.getAccessToken());
    }

    @Test
    void getrefreshToken() {
        assertEquals("sdfwe", new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L).getRefreshToken());
    }

    @Test
    void setrefreshToken() {
        User user = new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L);
        user.setRefreshToken("reqwt");
        assertEquals("reqwt", user.getRefreshToken());
    }

    @Test
    void getLoginTime() {
        assertEquals(1686392490523L, new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L).getLoginTime());
    }

    @Test
    void setLoginTime() {
        User user = new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L);
        user.setLoginTime(1686392490524L);
        assertEquals(1686392490524L, user.getLoginTime());
    }

}