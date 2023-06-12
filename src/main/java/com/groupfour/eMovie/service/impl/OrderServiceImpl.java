package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.OrderDao;
import com.groupfour.eMovie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    // 函数定义
}
