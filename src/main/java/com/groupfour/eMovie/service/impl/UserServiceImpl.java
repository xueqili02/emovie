package com.groupfour.eMovie.service.impl;

import com.groupfour.eMovie.dao.MovieDao;
import com.groupfour.eMovie.dao.UserDao;
import com.groupfour.eMovie.entity.*;
import com.groupfour.eMovie.service.UserService;
import com.groupfour.eMovie.utils.RandomCodeUtils;
import com.groupfour.eMovie.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.groupfour.eMovie.utils.ProjectConstants.*;
import static com.groupfour.eMovie.utils.RegexUtils.EmailValid;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final static String KEY = "groupfour";

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User registerUser(User user) {
        userDao.registerUser(user);
//        int id = user.getId();
//        user.setId(id);
        return user;
    }

    public Boolean existUser(User user) {
        return userDao.getUserByUsername(user.getUsername()) != null ||
                userDao.getUserByEmail(user.getEmail()) != null;
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
        rating.setMovieid(link.getId()); // 设置为没有用到的id
        userDao.rateMovie(rating);
    }

    public List<RatingRecord> getRatingRecord(int uid) {
        List<Rating> ratingList = userDao.getRatings(uid); // uid, id, rating
        List<RatingRecord> recordList = new ArrayList<>();
        // convert id
        for (Rating r: ratingList) {
            Movie movie = movieDao.getMovieByUnusedId(r.getMovieid());
            recordList.add(new RatingRecord(movie.getOriginalTitle(), r.getRating()));
        }
        return recordList;
    }

    public int sendCodeByEmail(LoginEmail loginEmail) {
        String email = loginEmail.getEmail();
        if (!EmailValid(email)) {
            return FAILURE;
        }

        String code = RandomCodeUtils.random(CODE_DIGITS);
        String subject = "eMovie validation code";
        String content = "eMovie validation code is: " + code + ". It is valid within 5 minutes.";
        new MailUtils().send(email, subject, content);

        stringRedisTemplate.opsForValue().set(REDIS_LOGIN_KEY_PREFIX + email, code, REDIS_LOGIN_TTL, TimeUnit.MINUTES);

        return SUCCESS;
    }

    public User loginByCode(LoginCode loginCode) {
        String email = loginCode.getEmail();
        if (!EmailValid(email)) {
            return null;
        }
        String code = loginCode.getCode();
        String cacheCode = stringRedisTemplate.opsForValue().get(REDIS_LOGIN_KEY_PREFIX + email);
        if (!code.equals(cacheCode)) {
            return null;
        }
        return userDao.getUserByEmail(email);
    }
}
