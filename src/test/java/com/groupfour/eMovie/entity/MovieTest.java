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

    @Test
    void getBudget() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals(222, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getBudget());
    }

    @Test
    void setBudget(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setBudget(333);
        assertEquals(333, movie.getBudget());
    }

    @Test
    void getOriginalLanguage() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals("English", new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getOriginalLanguage());
    }

    @Test
    void setOriginalLanguage(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setOriginalLanguage("Spanish");
        assertEquals("Spanish", movie.getOriginalLanguage());
    }

    @Test
    void getOriginalTitle() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals("Harry Potter", new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getOriginalTitle());
    }

    @Test
    void setOriginalTitle(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setOriginalTitle("Spiderman");
        assertEquals("Spiderman", movie.getOriginalTitle());
    }

    @Test
    void getPopularity() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals(1.8f, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getPopularity());
    }

    @Test
    void setPopularity(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setPopularity(1.6f);
        assertEquals(1.6f, movie.getPopularity());
    }

    @Test
    void getReleaseDate() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals("2011-11-04", new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getReleaseDate());
    }

    @Test
    void setReleaseDate(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setReleaseDate("2012-11-04");
        assertEquals("2012-11-04", movie.getReleaseDate());
    }

    @Test
    void getRevenue() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals(1000000, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getRevenue());
    }

    @Test
    void setRevenue(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setRevenue(100000);
        assertEquals(100000, movie.getRevenue());
    }

    @Test
    void getRuntime() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals(152, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getRuntime());
    }

    @Test
    void setRuntime(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setRuntime(151);
        assertEquals(151, movie.getRuntime());
    }

    @Test
    void getTitle() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals("Harry Potter", new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getTitle());
    }

    @Test
    void setTitle(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setTitle("Spiderman");
        assertEquals("Spiderman", movie.getTitle());
    }

    @Test
    void getVoteAverage() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals(10f, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getVoteAverage());
    }

    @Test
    void setVoteAverage(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setVoteAverage(15f);
        assertEquals(15f, movie.getVoteAverage());
    }

    @Test
    void getVoteCount() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals(3000, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getVoteCount());
    }

    @Test
    void setVoteCount(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setVoteCount(2000);
        assertEquals(2000, movie.getVoteCount());
    }

    @Test
    void getOverview() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        assertEquals("Good", new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getOverview());
    }

    @Test
    void setOverview(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        movie.setOverview("Amazing");
        assertEquals("Amazing", movie.getOverview());
    }

    @Test
    void getGenre() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
//        genre.add("b");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        List<String> gettedGenre = new ArrayList<>();
        gettedGenre.add("a");
        assertEquals(gettedGenre, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getGenre());
    }

    @Test
    void setGenre(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        genre.add("b");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        genre.add("c");
        movie.setGenre(genre);
        assertEquals(genre, movie.getGenre());
    }

    @Test
    void getKeyword() {
        List<String> genre = new ArrayList<>();
        genre.add("a");
//        genre.add("b");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        List<String> gettedKeyword = new ArrayList<>();
        gettedKeyword.add("brave");
        assertEquals(gettedKeyword, new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword).getKeyword());
    }

    @Test
    void setKeyword(){
        List<String> genre = new ArrayList<>();
        genre.add("a");
        genre.add("b");
        List<String> keyword = new ArrayList<>();
        keyword.add("brave");
        Movie movie = new Movie(1,222,"English","Harry Potter",1.8f,"2011-11-04",1000000,152,"Harry Potter",10f,3000,"Good",genre, keyword);
        keyword.add("b");
        movie.setKeyword(keyword);
        assertEquals(keyword, movie.getKeyword());
    }
}
