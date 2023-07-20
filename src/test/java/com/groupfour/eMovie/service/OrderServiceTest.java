package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.OrderDao;
import com.groupfour.eMovie.entity.Order;
import com.groupfour.eMovie.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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
    public void callInsertAnother() {
        Timestamp creat = new Timestamp(2023, 6, 17, 21, 30, 26, 0);
        Timestamp display = new Timestamp(2023, 6, 17, 21, 30, 26, 0);
        order = new Order(2, 168, creat, display, 99, "1");
        OrderServiceImpl orderService = Mockito.mock(OrderServiceImpl.class);
        Mockito.doAnswer(invocation -> {
            Object arg = invocation.getArgument(0);
            assertEquals(order, arg);
            return null;
        }).when(orderService).insertAnOrder(Mockito.any(Order.class));
        this.orderService.insertAnOrder(order);
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
