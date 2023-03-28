package com.groupfour.eMovie.entity;

public class Link {
    private int movieid;
    private int imdbid;
    private int tmdbid;

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getImdbid() {
        return imdbid;
    }

    public void setImdbid(int imdbid) {
        this.imdbid = imdbid;
    }

    public int getTmdbid() {
        return tmdbid;
    }

    public void setTmdbid(int tmdbid) {
        this.tmdbid = tmdbid;
    }
}
