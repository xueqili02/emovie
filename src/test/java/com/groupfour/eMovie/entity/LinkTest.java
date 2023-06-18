package com.groupfour.eMovie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkTest {
    @Test
    void getid() {
        assertEquals(1, new Link(1, 3,5).getId());
    }

    @Test
    void setid(){
        Link link = new Link(1, 3,5);
        link.setId(2);
        assertEquals(2, link.getId());
    }

    @Test
    void getImdbid() {
        assertEquals(3, new Link(1, 3,5).getImdbid());
    }

    @Test
    void setImdbid(){
        Link link = new Link(1, 3,5);
        link.setImdbid(2);
        assertEquals(2, link.getImdbid());
    }

    @Test
    void getTmdbid() {
        assertEquals(5, new Link(1, 3,5).getTmdbid());
    }

    @Test
    void setTmdbid(){
        Link link = new Link(1, 3,5);
        link.setTmdbid(2);
        assertEquals(2, link.getTmdbid());
    }

}
