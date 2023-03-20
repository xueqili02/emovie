package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Select("SELECT * FROM users WHERE refresh_token = #{refreshToken}")
    User getUserByRefreshToken(@Param("username") String refreshToken);

    @Insert("INSERT INTO users (username, password, token) VALUES (#{username}, #{password}, #{token})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer registerUser(User user);

    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User getUserByUsernameAndPassword(@Param("username") String username,
                                      @Param("password") String password);

    @Update("Update users SET token = #{user.token} where username = #{user.username}")
    void updateUser(@Param("user") User user);
}
