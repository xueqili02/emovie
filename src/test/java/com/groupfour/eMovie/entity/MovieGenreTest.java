package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieGenreTest {
    @Test
    void getMovieid() {
        assertEquals(1, new MovieGenre(1, 3).getMovieid());
    }

    @Test
    void setMovieid(){
        MovieGenre movieGenre = new MovieGenre(1, 3);
        movieGenre.setMovieid(2);
        assertEquals(2, movieGenre.getMovieid());
    }

    @Test
    void getGenreid() {
        assertEquals(3, new MovieGenre(1, 3).getGenreid());
    }

    @Test
    void setGenreid(){
        MovieGenre movieGenre = new MovieGenre(1, 1);
        movieGenre.setGenreid(2);
        assertEquals(2, movieGenre.getGenreid());
    }
}
