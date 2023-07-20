package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingTest {
    @Test
    void getUid() {
        assertEquals(1, new Rating(1, 3,1.5f).getUid());
    }

    @Test
    void setUid(){
        Rating rating = new Rating(1, 3,1.5f);
        rating.setUid(2);
        assertEquals(2, rating.getUid());
    }

    @Test
    void getMovieid() {
        assertEquals(3, new Rating(1, 3,1.5f).getMovieid());
    }

    @Test
    void setMovieid(){
        Rating rating = new Rating(1, 3,1.5f);
        rating.setMovieid(2);
        assertEquals(2, rating.getMovieid());
    }

    @Test
    void getRating() {
        assertEquals(1.5f, new Rating(1, 3,1.5f).getRating());
    }

    @Test
    void setRating(){
        Rating rating = new Rating(1, 3,1.5f);
        rating.setRating(1.6f);
        assertEquals(1.6f, rating.getRating());
    }
}
