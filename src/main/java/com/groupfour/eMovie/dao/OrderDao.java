package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDao {
    @Insert("INSERT INTO movie_order (userid, movieid, createTime, displayTime, seat, price)"+
    "VALUES (#{userid}, #{movieid}, #{createTime}, #{displayTime}, #{seat}, #{price})")
    void insertAnOrder(Order order);

    @Select("SELECT * FROM movie_order")
    List<Order> getAllOrders();

    @Select("SELECT * FROM movie_order WHERE userid=#{uid}")
    List<Order> getOrdersByUserId(int uid);
}
