package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.OrderDao;
import com.groupfour.eMovie.entity.Order;
import com.groupfour.eMovie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void insertAnOrder(Order order) {
        orderDao.insertAnOrder(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getOrdersByUserId(int uid) {
        return orderDao.getOrdersByUserId(uid);
    }

}
