package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccessTokenTest {
    @Test
    void getAccessToken() {
        assertEquals("abc", new AccessToken("abc", "12345").getAccessToken());
    }

    @Test
    void setAccessToken(){
        AccessToken accessToken = new AccessToken("abc", "12345");
        accessToken.setAccessToken("qwe");
        assertEquals("qwe", accessToken.getAccessToken());
    }

    @Test
    void getLoginTime() {
        assertEquals("12345", new AccessToken("abc", "12345").getLoginTime());
    }

    @Test
    void setALoginTime(){
        AccessToken accessToken = new AccessToken("abc", "12345");
        accessToken.setLoginTime("234567");
        assertEquals("234567", accessToken.getLoginTime());
    }
}

