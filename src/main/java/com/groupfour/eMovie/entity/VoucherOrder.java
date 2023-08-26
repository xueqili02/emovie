package com.groupfour.eMovie.entity;

import java.sql.Timestamp;

public class VoucherOrder {
    private long id;
    private int user_id;
    private long voucher_id;
    private byte status;
    private Timestamp create_time;
    private Timestamp pay_time;
    private Timestamp use_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(long voucher_id) {
        this.voucher_id = voucher_id;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getPay_time() {
        return pay_time;
    }

    public void setPay_time(Timestamp pay_time) {
        this.pay_time = pay_time;
    }

    public Timestamp getUse_time() {
        return use_time;
    }

    public void setUse_time(Timestamp use_time) {
        this.use_time = use_time;
    }
}
