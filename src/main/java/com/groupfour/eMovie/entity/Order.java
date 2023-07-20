package com.groupfour.eMovie.entity;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {
    private int userid;
    private int movieid;
    private Timestamp createTime;
    private Timestamp displayTime;
    private float price;
    private String seat;

    public Order() {

    }

    public Order(int userid, int movieid, Timestamp createTime, Timestamp displayTime, float price, String seat) {
        this.userid = userid;
        this.movieid = movieid;
        this.createTime = createTime;
        this.displayTime = displayTime;
        this.price = price;
        this.seat = seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userid, this.movieid, this.createTime,
                            this.displayTime, this.price, this.seat);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Order order = (Order) obj;
        return order.getUserid() == this.userid
                && order.getMovieid() == this.movieid
                && Objects.equals(order.getCreateTime(), this.createTime)
                && Objects.equals(order.getDisplayTime(), this.displayTime)
                && order.getPrice() == this.price
                && Objects.equals(order.getSeat(), this.seat);
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(Timestamp displayTime) {
        this.displayTime = displayTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
