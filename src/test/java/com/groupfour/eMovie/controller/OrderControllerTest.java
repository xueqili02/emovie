package com.groupfour.eMovie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupfour.eMovie.entity.Order;
import com.groupfour.eMovie.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@Transactional
public class OrderControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Mock
    private Order order;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    // write test at here

    @Test
    public void insertOrder() throws Exception {

        Timestamp creat = new Timestamp(2023, 6, 17, 21, 30, 26, 0);
        Timestamp display = new Timestamp(2023, 6, 17, 21, 30, 26, 0);
        order = new Order(2, 168, creat, display, 99, "1");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


//        RequestBuilder request1;
//        request1 = get("/orders/insert", "{\"uid444\": \"1\"}");
//        mockMvc.perform(request1)
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//        RequestBuilder request2;
//        request2 = get("/orders/insert", "{\"uid\": \"1\"}");
//        mockMvc.perform(request2)
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getOrders() throws Exception {
        RequestBuilder request;
        request = get("/orders");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getMovieById() throws Exception {
        RequestBuilder request;
        request = get("/orders/uid/{uid}", 1);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
