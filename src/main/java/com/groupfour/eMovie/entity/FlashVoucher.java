package com.groupfour.eMovie.entity;

import java.time.LocalDateTime;

public class FlashVoucher {
    private long id;
    private int stock;
    private LocalDateTime create_time;
    private LocalDateTime begin_time;
    private LocalDateTime end_time;
    private LocalDateTime update_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(LocalDateTime begin_time) {
        this.begin_time = begin_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }
}
