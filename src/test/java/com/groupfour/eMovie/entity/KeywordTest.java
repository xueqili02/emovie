package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeywordTest {
    @Test
    void getid() {
        assertEquals(1, new Keyword(1, "cat").getId());
    }

    @Test
    void setid(){
        Keyword keyword = new Keyword(1, "cat");
        keyword.setId(2);
        assertEquals(2, keyword.getId());
    }

    @Test
    void getKeyword() {
        assertEquals("cat", new Keyword(1, "cat").getKeyword());
    }

    @Test
    void setKeyword(){
        Keyword keyword = new Keyword(1, "cat");
        keyword.setKeyword("kitten");
        assertEquals("kitten", keyword.getKeyword());
    }
}
