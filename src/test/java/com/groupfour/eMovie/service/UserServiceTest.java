package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.UserDao;
import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserDao userDao;

    @Mock
    private User user;

    @Autowired
    private UserServiceImpl userService;

    @BeforeEach()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserByUsername() {
        String[] names = {"lixueqi","123"};
        for (String name : names) {
            user = userDao.getUserByUsername(name);
            assertEquals(user, userService.getUserByUsername(name));
        }
    }

    @Test
    public void registerUser() {
        String[] usernames = {"dk", "237"};
        String[] passwords = {"123", "5123"};
        for (int i = 0; i < usernames.length; i++) {
            User user = userService.registerUser(usernames[i], passwords[i]);
            assertEquals(user, userDao.getUserByUsernameAndPassword(usernames[i], passwords[i]));
        }
    }

    @Test
    public void existUser() {
        String[] usernames = {"lixueqi", "237"};
        for (String i : usernames) {
            boolean exist = userDao.getUserByUsername(i) != null;
            assertEquals(exist, userService.existUser(i));
        }
    }

    @Test
    public void loginValid() {
        String[] usernames = {"lixueqi", "237"};
        String[] passwords = {"123","5123"};
        for (String i : usernames) {
            for (String j : passwords) {
                user = userDao.getUserByUsernameAndPassword(i,j);
                assertEquals(user, userService.loginValid(i, j));
            }
        }
    }

//    @Test
//    public void callUpdateUser() {
//        UserService userService = Mockito.mock(UserService.class);
//        Mockito.doNothing().when(userService).updateUser(Mockito.isA(User.class));
//        userService.updateUser(user);
//        Mockito.verify(userService, Mockito.times(1)).updateUser(user);
//    }
//
//    @Test
//    public void callChangePassword() {
//
//    }
//
//    @Test
//    public void getRatingRecord() {
//        int[] uids = {1, 2};
//        for (int i : uids) {
//            List<Rating> ratingList = userDao.getRatings(i);
//            List<RatingRecord> recordList = userService.getRatingRecord(i);
//            assertEquals(recordList, );
//        }
//
//    }
}
