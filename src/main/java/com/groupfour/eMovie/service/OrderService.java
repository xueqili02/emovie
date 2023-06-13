package com.groupfour.eMovie.service;

import com.groupfour.eMovie.entity.Order;

import java.util.List;

public interface OrderService {
    // 函数声明
    void insertAnOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByUserId(int uid);

}
