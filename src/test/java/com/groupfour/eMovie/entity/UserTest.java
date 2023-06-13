package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    // entity测试样例，请仿照着写
    @Test
    void getId() {
        assertEquals(1, new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L).getId());
    }

    @Test
    void setId() {
        User user = new User(1, "lixueqi", "sdfwef", "1sfwe",
                "sdfwe", 1686392490523L);
        user.setId(2);
        assertEquals(2, user.getId());
    }
}
