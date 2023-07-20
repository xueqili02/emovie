package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieKeywordTest {
    @Test
    void getMovieid() {
        assertEquals(1, new MovieKeyword(1, 3).getMovieid());
    }

    @Test
    void setMovieid(){
        MovieKeyword movieKeyword = new MovieKeyword(1, 3);
        movieKeyword.setMovieid(2);
        assertEquals(2, movieKeyword.getMovieid());
    }

    @Test
    void getKeywordid() {
        assertEquals(3, new MovieKeyword(1, 3).getKeywordid());
    }

    @Test
    void setKeywordid(){
        MovieKeyword movieKeyword = new MovieKeyword(1, 3);
        movieKeyword.setKeywordid(2);
        assertEquals(2, movieKeyword.getKeywordid());
    }


}
