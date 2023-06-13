package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.UserDao;
import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Mock
    private User user;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // service测试样例，请仿照着写
    @Test
    public void getUserByUsername() {
        user = userDao.getUserByUsername("lixueqi");
        assertEquals(user, userService.getUserByUsername("lixueqi"));
    }
}
