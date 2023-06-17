package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {
    @Test
    void getid() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals(1, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getId());
    }

    @Test
    void setid(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setId(2);
        assertEquals(2, movie.getId());
    }
}
