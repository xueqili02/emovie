package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenreTest {
    @Test
    void getid() {
        assertEquals(1, new Genre(1, "cat").getId());
    }

    @Test
    void setid(){
        Genre genre = new Genre(1, "cat");
        genre.setId(2);
        assertEquals(2, genre.getId());
    }

    @Test
    void getgenre() {
        assertEquals("cat", new Genre(1, "cat").getGenre());
    }

    @Test
    void setgenre(){
        Genre genre = new Genre(1, "cat");
        genre.setGenre("kitten");
        assertEquals("kitten", genre.getGenre());
    }
}
