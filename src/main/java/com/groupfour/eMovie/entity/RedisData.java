package com.groupfour.eMovie.entity;

import java.time.LocalDateTime;

public class RedisData<T> {

    private LocalDateTime expireTime;
    private T object;

    public RedisData(LocalDateTime expireTime, T object)   {
        this.expireTime = expireTime;
        this.object = object;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
