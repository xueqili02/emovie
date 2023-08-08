package com.groupfour.eMovie;

import com.groupfour.eMovie.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name", "lixueqi");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    void testSaveUser() {
        redisTemplate.opsForValue().set("user:5", new User(100, "lotov", "sadfwef", null, null, 1234L));
        User user = (User) redisTemplate.opsForValue().get("user:5");
        System.out.println(user);
    }
}
