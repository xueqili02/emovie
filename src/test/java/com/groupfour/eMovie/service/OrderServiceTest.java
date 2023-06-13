package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.OrderDao;
import com.groupfour.eMovie.entity.Order;
import com.groupfour.eMovie.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {
    @Mock
    private OrderDao orderDao;

    @Mock
    private Order order;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
