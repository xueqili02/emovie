package com.groupfour.eMovie.entity;

public class AccessToken {
    private String accessToken;
    private String loginTime;

    public AccessToken(String accessToken, String loginTime) {
        this.accessToken = accessToken;
        this.loginTime = loginTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
