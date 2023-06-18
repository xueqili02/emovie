package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.Times;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    @Test
    void getUserid() {
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());

        assertEquals(1, new Order(1,  1, time1,time2,45f,"12a").getUserid());
    }

    @Test
    void setUserid(){
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        Order order = new Order(1,  1, time1,time2,45f,"12a");
        order.setUserid(2);
        assertEquals(2, order.getUserid());
    }

    @Test
    void getMovieid() {
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        assertEquals(1, new Order(1,  1, time1,time2,45f,"12a").getMovieid());
    }

    @Test
    void setMovieid(){
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        Order order = new Order(1,  1, time1,time2,45f,"12a");
        order.setMovieid(2);
        assertEquals(2, order.getMovieid());
    }

    @Test
    void getCreateTime() {
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());

        assertEquals(time1, new Order(1,1, time1,time2,45f,"12a").getCreateTime());
    }

    @Test
    void setCreateTime(){
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        Order order = new Order(1,  1, time1,time2,45f,"12a");
        Timestamp time3 = new Timestamp(System.currentTimeMillis());
        order.setCreateTime(time3);
        assertEquals(time3, order.getCreateTime());
    }

    @Test
    void getDisplayTime() {
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());

        assertEquals(time2, new Order(1,  1, time1,time2,45f,"12a").getDisplayTime());
    }

    @Test
    void setDisplayTime(){
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        Order order = new Order(1,  1, time1,time2,45f,"12a");
        Timestamp time3 = new Timestamp(System.currentTimeMillis());
        order.setDisplayTime(time3);
        assertEquals(time3, order.getDisplayTime());
    }

    @Test
    void getPrice() {
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());

        assertEquals(45f, new Order(1,  1, time1,time2,45f,"12a").getPrice());
    }

    @Test
    void setPrice(){
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        Order order = new Order(1,  1, time1,time2,45f,"12a");
        order.setPrice(2f);
        assertEquals(2f, order.getPrice());
    }

    @Test
    void getSeat() {
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());

        assertEquals("12a", new Order(1,  1, time1,time2,45f,"12a").getSeat());
    }

    @Test
    void setSeat(){
        Timestamp time1 = new Timestamp(System.currentTimeMillis());
        Timestamp time2 = new Timestamp(System.currentTimeMillis());
        Order order = new Order(1,  1, time1,time2,45f,"12a");
        order.setSeat("11a");
        assertEquals("11a", order.getSeat());
    }

}
