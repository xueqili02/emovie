package com.groupfour.eMovie.entity;

import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String password;
    private String accessToken;
    private String refreshToken;
    private long loginTime;

    public User() {

    }

    public User(int id, String username, String password, String accessToken, String refreshToken, long loginTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.loginTime = loginTime;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "user " + id + "\n" + username + "\n" + password + "\n" + accessToken + "\n" + refreshToken;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username,
                this.password, this.accessToken,
                this.refreshToken, this.refreshToken);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        User user = (User) obj;
        return user.getId() == this.id
                && Objects.equals(user.getUsername(), this.username)
                && Objects.equals(user.getPassword(), this.password)
                && Objects.equals(user.getAccessToken(), this.accessToken)
                && Objects.equals(user.getRefreshToken(), this.refreshToken)
                && user.getLoginTime() == this.loginTime;
    }
}
