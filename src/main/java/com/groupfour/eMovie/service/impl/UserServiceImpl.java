package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.MovieDao;
import com.groupfour.eMovie.dao.UserDao;
import com.groupfour.eMovie.entity.*;
import com.groupfour.eMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MovieDao movieDao;

    private final static String KEY = "groupfour";

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
//        user.setPassword(DigestUtils.md5DigestAsHex((password + KEY).getBytes()));
        user.setPassword(password);
        int id = userDao.registerUser(user);
        user.setId(id);
        return user;
    }

    public Boolean existUser(String username) {
        return userDao.getUserByUsername(username) != null;
    }

    public User loginValid(String username, String password) {
        return userDao.getUserByUsernameAndPassword(username,password);
//                DigestUtils.md5DigestAsHex((password + KEY).getBytes()));
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void changePassword(String newPassword, String username) {
        userDao.changePassword(newPassword, username);
    }

    public void rateMovie(Rating rating) {
        Link link = movieDao.getMovieLink(rating.getMovieid());
        rating.setMovieid(link.getId());
        userDao.rateMovie(rating);
    }

    public List<RatingRecord> getRatingRecord(int uid) {
        List<Rating> ratingList = userDao.getRatings(uid); // uid, movieid, rating
        List<RatingRecord> recordList = new ArrayList<>();
        // convert id
        for (Rating r: ratingList) {
            int tmdbid = movieDao.getTmdbidById(r.getMovieid());
            Movie movie = movieDao.getMovieById(tmdbid);
            recordList.add(new RatingRecord(movie.getOriginalTitle(), r.getRating()));
        }
        return recordList;
    }
}
