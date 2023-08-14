package com.groupfour.eMovie;

import com.google.gson.Gson;
import com.groupfour.eMovie.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString() {
        stringRedisTemplate.opsForValue().set("name", "lixueqi");
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
    }

    @Test
    void testSaveUser() {
        User user = new User(100, "lotov", "sadfwef", null, null, 1234L, "abc@gmail.com");
        stringRedisTemplate.opsForValue().set("user:5", new Gson().toJson(user));
        String jsonUser = stringRedisTemplate.opsForValue().get("user:5");
        User obtainedUser = new Gson().fromJson(jsonUser, User.class);
        System.out.println(obtainedUser); // toString()
    }
}
