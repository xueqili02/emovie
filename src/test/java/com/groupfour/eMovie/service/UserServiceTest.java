package com.groupfour.eMovie.service;

import com.groupfour.eMovie.dao.MovieDao;
import com.groupfour.eMovie.dao.UserDao;
import com.groupfour.eMovie.entity.Movie;
import com.groupfour.eMovie.entity.Rating;
import com.groupfour.eMovie.entity.RatingRecord;
import com.groupfour.eMovie.entity.User;
import com.groupfour.eMovie.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private MovieDao movieDao;

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

    @Test
    public void callUpdateUser() {
        userService.updateUser(user);
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        Mockito.doAnswer(invocation -> {
            Object arg = invocation.getArgument(0);
            assertEquals(user, arg);
            return null;
        }).when(userService).updateUser(Mockito.any(User.class));
        //Mockito.verify(userService, Mockito.times(1)).updateUser(user);
        this.userService.updateUser(user);
    }

    @Test
    public void callChangePassword() {
        String username = "lyx";
        String password = "123456";
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        Mockito.doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            Object arg1 = invocation.getArgument(1);
            assertEquals(username, arg1);
            assertEquals(password, arg0);
            return null;
        }).when(userService).changePassword(Mockito.any(String.class), Mockito.any(String.class));

        this.userService.changePassword(password, username);
    }

    @Test
    public void callRateMovie() {
        int uid = 2;
        int movieId = 168;
        float rating = 3;
        Rating r = new Rating(uid, movieId, rating);
        UserServiceImpl userService = Mockito.mock(UserServiceImpl.class);
        Mockito.doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            assertEquals(r, arg0);
            return null;
        }).when(userService).rateMovie(Mockito.any(Rating.class));

        this.userService.rateMovie(r);
    }

    @Test
    public void getRatingRecord() {
        int[] uids = {1, 2};
        for (int i : uids) {
            List<Rating> ratingList = userDao.getRatings(i);
            List<RatingRecord> recordList = new ArrayList<>();
            for (Rating r : ratingList) {
                Movie movie = movieDao.getMovieByUnusedId(r.getMovieid());
                recordList.add(new RatingRecord(movie.getOriginalTitle(), r.getRating()));
            }
            assertEquals(recordList, userService.getRatingRecord(i));
        }

    }
}
