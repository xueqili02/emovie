package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.Rating;
import com.groupfour.eMovie.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Select("SELECT * FROM users WHERE refreshToken = #{refreshToken}")
    User getUserByRefreshToken(@Param("refreshToken") String refreshToken);

    @Insert("INSERT INTO users (username, password, accessToken, refreshToken, loginTime)" +
            "VALUES (#{username}, #{password}, #{accessToken}, #{refreshToken}, #{loginTime})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer registerUser(User user);

    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User getUserByUsernameAndPassword(@Param("username") String username,
                                      @Param("password") String password);

    @Update("Update users SET accessToken = #{user.accessToken}, " +
            "refreshToken = #{user.refreshToken}, " +
            "loginTime = #{user.loginTime} " +
            "where username = #{user.username}")
    void updateUser(@Param("user") User user);

    @Update("UPDATE users SET password = #{newPassword} WHERE username = #{username}")
    void changePassword(@Param("newPassword") String newPassword,
                        @Param("username") String username);

    @Insert("INSERT INTO movie_ratings(uid, movieid, rating) VALUES(#{uid}, #{movieid}, #{rating})")
    void rateMovie(Rating rating);

    @Select("SELECT * FROM movie_ratings WHERE uid = #{uid}")
    List<Rating> getRatings(@Param("uid") int uid);
}
