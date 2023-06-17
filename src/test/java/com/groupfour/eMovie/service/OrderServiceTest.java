package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.OrderDao;
import com.groupfour.eMovie.entity.Order;
import com.groupfour.eMovie.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired
    private OrderDao orderDao;

    @Mock
    private Order order;

    @Autowired
    private OrderServiceImpl orderService;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void callGetAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        assertEquals(orders, orderDao.getAllOrders());
    }

    @Test
    public void callGetOrdersByUserId() {
        int[] userIds = {1, 21};
        for (int id : userIds) {
            List<Order> orders = orderDao.getOrdersByUserId(id);
            assertEquals(orders, orderService.getOrdersByUserId(id));
        }
    }
}
