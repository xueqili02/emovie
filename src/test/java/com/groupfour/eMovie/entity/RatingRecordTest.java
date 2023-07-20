package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingRecordTest {
    @Test
    void getOriginalTitle() {
        assertEquals("Harry Potter", new RatingRecord("Harry Potter", 1.5f).getOriginalTitle());
    }

    @Test
    void setOriginalTitle(){
        RatingRecord ratingRecord = new RatingRecord("Harry Potter", 1.5f);
        ratingRecord.setOriginalTitle("Spiderman");
        assertEquals("Spiderman", ratingRecord.getOriginalTitle());
    }

    @Test
    void getRating() {
        assertEquals(1.5f, new RatingRecord("Harry Potter", 1.5f).getRating());
    }

    @Test
    void setRating(){
        RatingRecord ratingRecord = new RatingRecord("Harry Potter", 1.5f);
        ratingRecord.setRating(1.6f);
        assertEquals(1.6f, ratingRecord.getRating());
    }
}
