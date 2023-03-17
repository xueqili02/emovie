package com.groupfour.eMovie.dao;

import com.groupfour.eMovie.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO users (username, password, token) VALUES (#{username}, #{password}, #{token})")
    @Options(keyColumn = "id", keyProperty = "id", useGeneratedKeys = true)
    Integer registerUser(User user);

}
